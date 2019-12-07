package ar.tesis.payments.service;

import ar.tesis.payments.model.Seller;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//("SellerServiceInterface")
public interface SellerServiceInterface {

    void saveSeller(Seller seller);
    void updateSeller(Seller seller);
    Seller findSellerByUserName(String sellerId);
    List<Seller> findAll();
}
