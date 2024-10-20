package com.rincondeltaco.vouchers_service.repository;

import com.rincondeltaco.vouchers_service.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
}