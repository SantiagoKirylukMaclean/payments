package ar.tesis.payments.controller;

import ar.tesis.payments.model.Seller;
import ar.tesis.payments.model.User;
import ar.tesis.payments.modelDTO.RequestBackupDTO;
import ar.tesis.payments.service.ManagementServiceInterface;
import ar.tesis.payments.service.SellerServiceInterface;
import ar.tesis.payments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ManagementServiceInterface managementServiceInterface;

    @Autowired
    //@Qualifier("Default2SellerService")
    private SellerServiceInterface sellerServiceInterface;

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView Adminhome(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value="/admin/restore_bkp", method = RequestMethod.GET)
    public ModelAndView listBackup(){
        ModelAndView modelAndView = new ModelAndView();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = new User();
        modelAndView.addObject("backups", managementServiceInterface.listBackup());
        modelAndView.addObject("requestbkp", new RequestBackupDTO());
        modelAndView.setViewName("admin/restore_bkp");
        return modelAndView;
    }

    @RequestMapping(value="/admin/backup/restore", method = RequestMethod.POST)
    public ModelAndView restoreBkp(RequestBackupDTO requestBackupDTO){
        ModelAndView modelAndView = new ModelAndView();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = new User();
        modelAndView.addObject("backups", managementServiceInterface.listBackup());
        modelAndView.addObject("requestbkp", new RequestBackupDTO());
        modelAndView.setViewName("admin/restore_bkp");
        return modelAndView;
    }

    @RequestMapping(value="/admin/backup", method = RequestMethod.POST)
    public ModelAndView makeBkp(){
        ModelAndView modelAndView = new ModelAndView();
        managementServiceInterface.makeBackup();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = new User();
        modelAndView.addObject("backups", managementServiceInterface.listBackup());
        modelAndView.addObject("requestbkp", new RequestBackupDTO());
        modelAndView.setViewName("admin/restore_bkp");
        return modelAndView;
    }

    @RequestMapping(value="/admin/users", method = RequestMethod.GET)
    public ModelAndView AdminCustomerGetUsers(){
        ModelAndView modelAndView = new ModelAndView();
        List<Seller> sellers = sellerServiceInterface.findAll();
        modelAndView.addObject("sellers", sellers);
        modelAndView.setViewName("admin/users");
        return modelAndView;
    }



}
