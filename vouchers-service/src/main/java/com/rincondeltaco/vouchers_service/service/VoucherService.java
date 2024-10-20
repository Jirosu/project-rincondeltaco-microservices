package com.rincondeltaco.vouchers_service.service;

import com.rincondeltaco.vouchers_service.models.Voucher;
import com.rincondeltaco.vouchers_service.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRep;

    public List<Voucher> getVouchers() { return voucherRep.findAll(); }

    public Voucher getVoucherById(int id) {
        return voucherRep.findById(id).orElse(null);
    }

    public Voucher addVoucher(Voucher voucher) {
        return voucherRep.save(voucher);
    }

    public Optional<Voucher> deleteVoucherById(int id) {
        Optional<Voucher> voucherOptional = voucherRep.findById(id);
        voucherOptional.ifPresent(voucher -> {
            voucherRep.delete(voucher);
        });
        return voucherOptional;
    }
}
