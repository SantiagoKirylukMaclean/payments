package ar.tesis.payments.repository;

import ar.tesis.payments.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {

    Market findById(int marketId);
}
