package ar.tesis.payments.controller;

import ar.tesis.payments.model.Seller;
import ar.tesis.payments.model.Servicio;
import ar.tesis.payments.service.ServicioServiceInterface;
import ar.tesis.payments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {


    @Autowired
    private ServicioServiceInterface servicioServiceInterface;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        Seller seller = new Seller();
        List<Servicio> servicios = servicioServiceInterface.getServices();

        modelAndView.addObject("servicios", servicios);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("successMessage", "");
        modelAndView.setViewName("login");
        return modelAndView;
    }






}