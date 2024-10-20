package com.rincondeltaco.orders_service.repository;

import com.rincondeltaco.orders_service.models.DetallePedido;
import com.rincondeltaco.orders_service.models.DetallePedidoID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoID> {
}
