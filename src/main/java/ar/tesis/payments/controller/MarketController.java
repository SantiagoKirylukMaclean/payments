package ar.tesis.payments.controller;

import ar.tesis.payments.model.Configuracion;
import ar.tesis.payments.model.Market;
import ar.tesis.payments.model.Seller;
import ar.tesis.payments.model.User;
import ar.tesis.payments.service.ConfiguracionServiceInterface;
import ar.tesis.payments.service.MarketServiceInterface;
import ar.tesis.payments.service.SellerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MarketController {

    @Autowired
    private MarketServiceInterface marketServiceInterface;

    @Autowired
    private SellerServiceInterface sellerServiceInterface;

    @Autowired
    private ConfiguracionServiceInterface configuracionServiceInterface;

    @RequestMapping(value="/market", method = RequestMethod.POST)
    public ModelAndView saveMarket(@Valid Market market, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = sellerServiceInterface.findSellerByUserName(auth.getName());
        seller.setMarket(market);
        sellerServiceInterface.updateSeller(seller);

        Configuracion configuracion = new Configuracion();
        if (seller.getConfiguracion() != null){
            configuracion = configuracionServiceInterface.findConfiguracionById(seller.getConfiguracion().getId());
        }
        modelAndView.addObject("successMessage", "El Market ha sido creado correctamente");
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("market", market);
        modelAndView.addObject("configuracion", configuracion);
        modelAndView.setViewName("user/users");
        return modelAndView;
    }
}
