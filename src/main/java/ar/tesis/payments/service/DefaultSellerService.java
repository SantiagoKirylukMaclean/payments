package ar.tesis.payments.service;


import ar.tesis.payments.model.Role;
import ar.tesis.payments.model.Seller;
import ar.tesis.payments.model.User;
import ar.tesis.payments.repository.RoleRepository;
import ar.tesis.payments.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("DefaultSellerService")
public class DefaultSellerService implements SellerServiceInterface {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public DefaultSellerService(BCryptPasswordEncoder bCryptPasswordEncoder,RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Autowired
    private SellerRepository sellerRepository;

    public void saveSeller(Seller seller){
        seller.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
        seller.setActive(1);
        Role userRole = roleRepository.findByRole("ROLE_USER");
        seller.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        sellerRepository.save(seller);
    }

    public void updateSeller(Seller seller){
        if (seller.getMarket() != null && seller.getConfiguracion() != null) seller.setStatus((byte)1);
        sellerRepository.save(seller);
    }

    public Seller findSellerByUserName(String sellerId) {
        return sellerRepository.findByUsername(sellerId);
    }

}
