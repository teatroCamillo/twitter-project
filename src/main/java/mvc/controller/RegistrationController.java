package mvc.controller;

import mvc.model.dto.UserDTO;
import mvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    String showRegistration(Model model){
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    String addUser(@ModelAttribute UserDTO userDTO){
        userService.create(userDTO);
        return "successful-registration";
    }

}
