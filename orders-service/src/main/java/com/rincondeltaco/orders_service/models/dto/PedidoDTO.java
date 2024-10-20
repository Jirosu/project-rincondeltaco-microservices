package com.rincondeltaco.orders_service.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private int idUsuario;
    private String telefonoEntrega;
    private String direccionEntrega;
    private String distritoEntrega;
}
