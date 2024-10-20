package com.rincondeltaco.orders_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int idPedido;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_order")
    private Date fechaPedido;

    @Column(name = "id_user")
    private int idUsuario;

    @Column(name = "subtotal_order")
    private double subtotal;

    @Column(name = "igv_order")
    private double igv;

    @Column(name = "total_order")
    private double total;

    @Column(name = "phone")
    private String telefonoEntrega;

    @Column(name = "address")
    private String direccionEntrega;

    @Column(name = "district")
    private String distritoEntrega;


    public Pedido(int idUsuario, String telefonoEntrega, String direccionEntrega, String distritoEntrega) {
        this.fechaPedido = new Date();
        this.idUsuario = idUsuario;
        this.telefonoEntrega = telefonoEntrega;
        this.direccionEntrega = direccionEntrega;
        this.distritoEntrega = distritoEntrega;
    }

    public Pedido(int idPedido) {
        this.idPedido = idPedido;
    }
}
