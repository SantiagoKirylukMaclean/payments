package ar.tesis.payments.service;

import ar.tesis.payments.model.Seller;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service//("SellerServiceInterface")
public interface SellerServiceInterface {

    void saveSeller(Seller seller);
    void updateSeller(Seller seller);
    Seller findSellerByUserName(String sellerId);
}
