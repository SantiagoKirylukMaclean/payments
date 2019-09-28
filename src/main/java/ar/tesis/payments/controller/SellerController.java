package ar.tesis.payments.controller;

import ar.tesis.payments.model.Configuracion;
import ar.tesis.payments.model.Market;
import ar.tesis.payments.model.Seller;
import ar.tesis.payments.model.Servicio;
import ar.tesis.payments.service.ConfiguracionServiceInterface;
import ar.tesis.payments.service.MarketServiceInterface;
import ar.tesis.payments.service.SellerServiceInterface;
import ar.tesis.payments.service.ServicioServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class SellerController {

    @Autowired
    private SellerServiceInterface sellerServiceInterface;

    @Autowired
    private MarketServiceInterface marketServiceInterface;

    @Autowired
    private ServicioServiceInterface servicioServiceInterface;

    @Autowired
    private ConfiguracionServiceInterface configuracionServiceInterface;

    @RequestMapping(value="/seller", method = RequestMethod.GET)
    public ModelAndView getSeller(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = sellerServiceInterface.findSellerByUserName(auth.getName());
        Market market = new Market();
        Configuracion configuracion = new Configuracion();
        if (seller.getMarket() != null){
            market = marketServiceInterface.findMarketById(seller.getMarket().getId());
        }
        if (seller.getConfiguracion() != null){
            configuracion = configuracionServiceInterface.findConfiguracionById(seller.getConfiguracion().getId());
        }

        modelAndView.addObject("market", market);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("configuracion", configuracion);
        modelAndView.setViewName("user/users");
        return modelAndView;
    }


}
