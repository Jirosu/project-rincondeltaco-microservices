package com.rincondeltaco.vouchers_service.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "vouchers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_voucher;
    @Temporal(TemporalType.DATE)
    private Date date_voucher;
    private double subtotal_voucher;
    private double igv_voucher;
    private double total_voucher;
    private int id_user;
    private String document_type_Cli;
    private String num_document_Cli;
    private String voucher_type;
    private int id_order;
}