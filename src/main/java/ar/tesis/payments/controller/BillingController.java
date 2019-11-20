package ar.tesis.payments.controller;

import ar.tesis.payments.model.Descuento;
import ar.tesis.payments.model.Seller;
import ar.tesis.payments.model.Transaction;
import ar.tesis.payments.service.ConfiguracionServiceInterface;
import ar.tesis.payments.service.DescuentoServiceInterface;
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
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class BillingController {

    @Autowired
    private DescuentoServiceInterface descuentoServiceInterface;

    @Autowired
    private SellerServiceInterface sellerServiceInterface;

    @Autowired
    private ConfiguracionServiceInterface configuracionServiceInterface;

    @RequestMapping(value="/billing", method = RequestMethod.GET)
    public ModelAndView getMarket(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = sellerServiceInterface.findSellerByUserName(auth.getName());
        double facturacionTotal = 0;
        for (Transaction trx : seller.getTransaction().stream().filter(trx -> trx.getEstado() == 2).collect(Collectors.toList())) {
            facturacionTotal = facturacionTotal + trx.getMonto();

        }

        modelAndView.addObject("transacciones", seller.getTransaction() == null ? new ArrayList<Transaction>() : seller.getTransaction());
        modelAndView.addObject("facturacion", seller.getTransaction()
                .stream()
                .filter(trx -> trx.getEstado() == 2 )
                .collect(Collectors.toList())
                .size() * seller.getServicio().getCostoTransaccion());
        modelAndView.addObject("facturacionTotal", facturacionTotal);
        modelAndView.setViewName("user/billing");
        return modelAndView;
    }


}
