package ar.tesis.payments.controller;

import ar.tesis.payments.model.Configuracion;
import ar.tesis.payments.model.Descuento;
import ar.tesis.payments.model.Market;
import ar.tesis.payments.model.Seller;
import ar.tesis.payments.service.ConfiguracionServiceInterface;
import ar.tesis.payments.service.DescuentoServiceInterface;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class DescuentoController {

    @Autowired
    private DescuentoServiceInterface descuentoServiceInterface;

    @Autowired
    private SellerServiceInterface sellerServiceInterface;

    @Autowired
    private ConfiguracionServiceInterface configuracionServiceInterface;

    @RequestMapping(value="/discounts", method = RequestMethod.GET)
    public ModelAndView getMarket(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = sellerServiceInterface.findSellerByUserName(auth.getName());



        modelAndView.addObject("descuento", seller.getDescuento() == null ? new ArrayList<Descuento>() : seller.getDescuento());
        modelAndView.addObject("descuento2", new Descuento());
        modelAndView.setViewName("user/discounts");
        return modelAndView;
    }

    @RequestMapping(value="/descuento", method = RequestMethod.POST)
    public ModelAndView saveDescuento(@Valid Descuento descuento, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = sellerServiceInterface.findSellerByUserName(auth.getName());
        List<Descuento> descuentos = seller.getDescuento() == null ? new ArrayList<Descuento>() : seller.getDescuento();
        descuentos.add(descuento);
        seller.setDescuento(descuentos);
        sellerServiceInterface.updateSeller(seller);


        modelAndView.addObject("successMessage", "El Descuento ha sido creado correctamente");
        modelAndView.addObject("descuento", seller.getDescuento() == null ? new ArrayList<Descuento>() : seller.getDescuento());
        modelAndView.addObject("descuento2", new Descuento());
        modelAndView.addObject("seller", seller);

        modelAndView.setViewName("user/discounts");
        return modelAndView;
    }
}
