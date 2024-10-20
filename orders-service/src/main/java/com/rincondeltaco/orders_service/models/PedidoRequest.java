package com.rincondeltaco.orders_service.models;

import com.rincondeltaco.orders_service.models.dto.DetallePedidoDTO;
import com.rincondeltaco.orders_service.models.dto.PedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
    private PedidoDTO datosPedido;
    private List<DetallePedidoDTO> listaProductos;
}
