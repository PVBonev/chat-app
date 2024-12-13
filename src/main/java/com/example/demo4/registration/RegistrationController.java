package com.example.demo4.registration;

import lombok.AllArgsConstructor;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    private final static Logger LOGGER = Logger.getLogger(RegistrationController.class.getName());

    @GetMapping
    public ModelAndView getRegistration() {//model and biew so it loads the html
        return new ModelAndView("registration");
    }

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public RedirectView confirm(@RequestParam("token") String token) {
        LOGGER.info("Recieved request to confirm token: " + token);
        registrationService.confirmToken(token);
        return new RedirectView("http://localhost:8080/login");
    }
}
