package ar.tesis.payments.controller;

import ar.tesis.payments.model.Configuracion;
import ar.tesis.payments.model.Market;
import ar.tesis.payments.model.Seller;
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
public class ConfiguracionController {

    @Autowired
    private SellerServiceInterface sellerServiceInterface;

    @Autowired
    private MarketServiceInterface marketServiceInterface;

    @RequestMapping(value="/configuracion", method = RequestMethod.POST)
    public ModelAndView saveConfiguracion(@Valid Configuracion configuracion, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = sellerServiceInterface.findSellerByUserName(auth.getName());
        seller.setConfiguracion(configuracion);
        sellerServiceInterface.updateSeller(seller);
        Market market = new Market();
        if (seller.getMarket() != null){
            market = marketServiceInterface.findMarketById(seller.getMarket().getId());
        }
        modelAndView.addObject("successMessage", "La configuracion ha sido creado correctamente");
        modelAndView.addObject("market", market);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("configuracion", configuracion);
        modelAndView.setViewName("user/users");
        return modelAndView;
    }
}
