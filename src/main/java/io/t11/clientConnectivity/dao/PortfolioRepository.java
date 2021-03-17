package io.t11.clientConnectivity.dao;

import io.t11.clientConnectivity.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
