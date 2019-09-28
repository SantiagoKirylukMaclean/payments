package ar.tesis.payments.controller;

import ar.tesis.payments.model.Seller;
import ar.tesis.payments.model.Servicio;
import ar.tesis.payments.model.User;
import ar.tesis.payments.service.SellerServiceInterface;
import ar.tesis.payments.service.ServicioServiceInterface;
import ar.tesis.payments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    //@Qualifier("Default2SellerService")
    private SellerServiceInterface sellerServiceInterface;

    @Autowired
    private ServicioServiceInterface servicioServiceInterface;

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Seller seller = new Seller();

        List<Servicio> servicios = servicioServiceInterface.getServices();
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("servicios", servicios);

        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration2", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewSeller(@Valid Seller seller, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        /*User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {*/
            sellerServiceInterface.saveSeller(seller);

            modelAndView.addObject("successMessage", "Seller has been registered successfully");
            modelAndView.addObject("seller", new Seller());
            modelAndView.setViewName("login");
        //}
        return modelAndView;
    }

    @RequestMapping(value="/user/home", method = RequestMethod.GET)
    public ModelAndView userHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/home");
        return modelAndView;
    }

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public ModelAndView AdminCustomerGetUsers(){
        ModelAndView modelAndView = new ModelAndView();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin/users");
        return modelAndView;
    }


}
