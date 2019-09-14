package ar.tesis.payments.controller;

import ar.tesis.payments.model.User;
import ar.tesis.payments.service.UserService;
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
public class AdminController {



    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView Adminhome(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }



}
