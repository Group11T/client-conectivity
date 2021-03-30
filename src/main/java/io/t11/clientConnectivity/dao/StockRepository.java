package io.t11.clientConnectivity.dao;

import io.t11.clientConnectivity.model.Stock;
import io.t11.clientConnectivity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Stock findByUniqueId(String uniqueId);

    Stock findByUser(User user);

    List<Stock> findAllByUser(User user);
}
