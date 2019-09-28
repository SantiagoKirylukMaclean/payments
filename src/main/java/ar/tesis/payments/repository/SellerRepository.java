package ar.tesis.payments.repository;

import ar.tesis.payments.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("SellerRepository")
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findByUsername(String sellerId);
}
