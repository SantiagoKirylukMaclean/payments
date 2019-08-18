package ar.tesis.payments.controller;

import ar.tesis.payments.configuration.JwtProvider;
import ar.tesis.payments.model.*;
import ar.tesis.payments.repository.RoleRepository;
import ar.tesis.payments.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;



    @GetMapping("/login")
        public ModelAndView login(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
        public ModelAndView authenticateUser(@Valid LoginForm loginRequest) {
            ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
            modelAndView.addObject("mensaje",ResponseEntity.ok(new JwtResponse(jwt)));

            modelAndView.setViewName("registro");
            return modelAndView;

    }

    @PostMapping("/registration")
    public ModelAndView registerUser(@Valid SignUpForm signUpRequest) {
        ModelAndView modelAndView = new ModelAndView();
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            modelAndView.addObject("mensaje", new ResponseEntity<String>("Fail -> Username is already taken!", HttpStatus.BAD_REQUEST));
            //return new ResponseEntity<String>("Fail -> Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            modelAndView.addObject("mensaje",new ResponseEntity<String>("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST));
            //return new ResponseEntity<String>("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: admin Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: pm Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: user Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);
        modelAndView.addObject("mensaje",ResponseEntity.ok().body("User registered successfully!"));

        modelAndView.setViewName("registro");
        return modelAndView;
    }



}
