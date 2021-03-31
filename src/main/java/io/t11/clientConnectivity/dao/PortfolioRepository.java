package io.t11.clientConnectivity.dao;

import io.t11.clientConnectivity.model.Portfolio;
import io.t11.clientConnectivity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Portfolio findByUser(User user);

    Portfolio findByTicker(String ticker);

    List<Portfolio> findAllByUser(User user);
}
