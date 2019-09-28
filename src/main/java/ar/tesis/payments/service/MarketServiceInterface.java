package ar.tesis.payments.service;

import ar.tesis.payments.model.Market;
import org.springframework.stereotype.Service;

@Service
public interface MarketServiceInterface {

    Market findMarketById(int marketId);

    void saveMarket(Market market);
}
