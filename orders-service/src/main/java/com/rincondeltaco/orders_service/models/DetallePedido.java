package com.rincondeltaco.orders_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DetallePedidoID.class)
public class DetallePedido {

    @Id
    @Column(name = "id_order")
    private int idPedido;

    @Id
    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "quantity")
    private int cantidad;

    @Column(name = "amount")
    private double monto;

    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private Pedido pedido;


}
