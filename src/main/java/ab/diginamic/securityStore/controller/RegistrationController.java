package ab.diginamic.securityStore.controller;

import ab.diginamic.securityStore.dto.UserDTO;
import ab.diginamic.securityStore.model.User;
import ab.diginamic.securityStore.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registerUserAccount(@ModelAttribute("user") UserDTO userDTO, BindingResult result) {
        System.out.println("registerUserAccount(): " + userDTO);
        if (result.hasErrors()) {
            return "registration";
        } else {
        User registered = registrationService.registerNewUserAccount(userDTO);
        return "registration-success";
        }
    }

    @GetMapping("/account")
    public String showMyInformation(Model model) {
        /* Authentication : Who is doing the request ? */
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Name: " + authentication.getName());
        System.out.println("authentication: " + authentication);
        System.out.println("principal: " + authentication.getPrincipal());
        model.addAttribute("principal", authentication.getPrincipal());
        model.addAttribute("user", registrationService.getUser(authentication.getName()));
        return "account";
    }
}
