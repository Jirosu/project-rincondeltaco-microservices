package com.rincondeltaco.orders_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherRequest {
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
