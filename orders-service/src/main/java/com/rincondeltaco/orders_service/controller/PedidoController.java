package com.rincondeltaco.orders_service.controller;

import com.rincondeltaco.orders_service.models.Pedido;
import com.rincondeltaco.orders_service.models.PedidoRequest;
import com.rincondeltaco.orders_service.service.DetallePedidoService;
import com.rincondeltaco.orders_service.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> getProducts() {
        return ResponseEntity.ok(pedidoService.getPedidos());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Pedido> getProducts(@PathVariable int id) {
        return ResponseEntity.ok(pedidoService.getPedidoById(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<Pedido> createPedido(@RequestBody PedidoRequest request) {

        return ResponseEntity.ok(pedidoService.addPedido(request));
    }


}
