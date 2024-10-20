package com.rincondeltaco.orders_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoID implements Serializable {

    private int idPedido;
    private int idProduct;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedidoID that = (DetallePedidoID) o;
        return idPedido == that.idPedido && idProduct == that.idProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, idProduct);
    }
}
