package io.t11.clientConnectivity.dao;

import io.t11.clientConnectivity.model.CreatedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatedOrderRepository extends JpaRepository<CreatedOrder,Long> {
}
