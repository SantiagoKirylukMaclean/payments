package ar.tesis.payments.service;

import java.util.List;

import ar.tesis.payments.model.User;
import ar.tesis.payments.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getUsers(){
        return repository.findAll();

    }

}
