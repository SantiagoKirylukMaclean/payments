package ar.tesis.payments.service;


import ar.tesis.payments.model.Market;
import ar.tesis.payments.model.Seller;
import ar.tesis.payments.repository.MarketRepository;
import ar.tesis.payments.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DefaultMarketService")
public class DefaultMarketService implements MarketServiceInterface{

    @Autowired
    private MarketRepository marketRepository;

    public Market findMarketById(int marketId) {
        return marketRepository.findById(marketId);
    }

    public void saveMarket(Market market){
        marketRepository.save(market);
    }
}
