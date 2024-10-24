package com.rincondeltaco.orders_service.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO {
    private String document_type_Cli;
    private String num_document_Cli;
    private String voucher_type;
}
