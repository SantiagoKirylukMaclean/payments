package ar.tesis.payments.controller;

import java.util.List;

import ar.tesis.payments.model.User;
import ar.tesis.payments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> findAllUsers() {
        return service.getUsers();

    }

}

