package com.rincondeltaco.orders_service.service;

import com.rincondeltaco.orders_service.models.DetallePedido;
import com.rincondeltaco.orders_service.models.Pedido;
import com.rincondeltaco.orders_service.models.PedidoRequest;
import com.rincondeltaco.orders_service.models.VoucherRequest;
import com.rincondeltaco.orders_service.models.dto.DetallePedidoDTO;
import com.rincondeltaco.orders_service.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Autowired
    private RestTemplate restTemplate;

    private final String PRODUCT_SERVICE_URL = "http://localhost:8082/producto/listar-precios";
    private final String VOUCHER_SERVICE_URL = "http://localhost:8085/voucher/crear";


    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido addPedido(PedidoRequest request) {
        Pedido pedido = new Pedido(request.getDatosPedido().getIdUsuario(), request.getDatosPedido().getTelefonoEntrega(), request.getDatosPedido().getDireccionEntrega(), request.getDatosPedido().getDistritoEntrega());
        pedido = calculatePedido(pedido, request.getListaProductos());

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        saveDetallePedido(request.getListaProductos(), pedidoGuardado.getIdPedido());
        generateComprobante(request, pedidoGuardado);

        return pedidoGuardado;
    }

    public Pedido calculatePedido(Pedido pedido, List<DetallePedidoDTO> productosPedido) {
        double subtotal = 0, igv = 0, total = 0, delivery = 15;

        List<Double> precios = getPrecioProductos(productosPedido);

        for (int i = 0; i < productosPedido.size(); i++) {
            subtotal += productosPedido.get(i).getCantidad() * precios.get(i);
        }

        igv = subtotal * 0.18;
        total = delivery + subtotal;

        pedido.setSubtotal(subtotal);
        pedido.setIgv(igv);
        pedido.setTotal(total);
        return pedido;
    }

    public List<Double> getPrecioProductos(List<DetallePedidoDTO> productosPedido) {
        //listado id's
        List<Integer> listadoIdsProd = new ArrayList<>();
        productosPedido.forEach(id -> listadoIdsProd.add(id.getIdProduct()));

        //obtener precios
        HttpEntity<List<Integer>> requestBody = new HttpEntity<>(listadoIdsProd);
        ResponseEntity<List> response= restTemplate.postForEntity(PRODUCT_SERVICE_URL, requestBody, List.class);
        List<Double> precios = response.getBody();

        return precios;
    }

    public List<DetallePedido> getDetallePedido(List<DetallePedidoDTO> productosPedido, int pedidoID) {
        List<DetallePedido> detallePedidos = new ArrayList<>();

        List<Double> precios = getPrecioProductos(productosPedido);

        for (int i = 0; i < productosPedido.size(); i++) {
            DetallePedido detPed = new DetallePedido( pedidoID,
                    productosPedido.get(i).getIdProduct(),
                    productosPedido.get(i).getCantidad(),
                    productosPedido.get(i).getCantidad() * precios.get(i),
                    null );

            detallePedidos.add(detPed);
        }
        return detallePedidos;
    }

    public void saveDetallePedido(List<DetallePedidoDTO> productosPedidoDTO, int pedidoID) {
        List<DetallePedido> productosPedido = getDetallePedido(productosPedidoDTO, pedidoID);
        productosPedido.forEach( prodPedido -> detallePedidoService.addDetallePedido(prodPedido));
    }

    public void generateComprobante(PedidoRequest request, Pedido pedidoGuardado) {
        VoucherRequest voucher = new VoucherRequest(pedidoGuardado.getFechaPedido(), pedidoGuardado.getSubtotal(),
                pedidoGuardado.getIgv(), pedidoGuardado.getTotal(), pedidoGuardado.getIdUsuario(),
                request.getDatosVoucher().getDocument_type_Cli(), request.getDatosVoucher().getNum_document_Cli(),
                request.getDatosVoucher().getVoucher_type(), pedidoGuardado.getIdPedido());

        restTemplate.postForEntity(VOUCHER_SERVICE_URL, voucher, VoucherRequest.class);
    }
}
