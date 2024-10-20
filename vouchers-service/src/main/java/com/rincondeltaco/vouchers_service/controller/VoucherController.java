package com.rincondeltaco.vouchers_service.controller;

import com.rincondeltaco.vouchers_service.models.Voucher;
import com.rincondeltaco.vouchers_service.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping("/listar")
    public ResponseEntity<List<Voucher>> getVouchers() {
        return ResponseEntity.ok(voucherService.getVouchers());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Voucher> getVoucher(@PathVariable int id) {
        Voucher voucher = voucherService.getVoucherById(id);
        if(voucher != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(voucher);
        }
        return ResponseEntity.ok(voucher);
    }
    @PostMapping("/crear")
    public ResponseEntity<Voucher> createVoucher(@RequestBody Voucher voucher) {
        return ResponseEntity.ok(voucherService.addVoucher(voucher));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteVoucher(@PathVariable int id) {
        Optional<Voucher> voucherOptional = voucherService.deleteVoucherById(id);
        if(voucherOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }
}
