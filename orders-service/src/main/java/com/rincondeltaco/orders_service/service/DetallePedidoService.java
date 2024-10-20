package com.rincondeltaco.orders_service.service;

import com.rincondeltaco.orders_service.models.DetallePedido;
import com.rincondeltaco.orders_service.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoService;

    public List<DetallePedido> getPedidos() {
        return detallePedidoService.findAll();
    }

    public DetallePedido addDetallePedido(DetallePedido detallePedido) {
        return detallePedidoService.save(detallePedido);
    }
}
