package mvc.controller;

import mvc.model.dto.UserDTO;
import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    /**
     * Returns /registration by GET method.
     */
    @GetMapping("/registration")
    public ModelAndView showRegistration(){
        return new ModelAndView("registration");
    }

    /**
     * Executes creating new user by POST method and return /successful-registration.
     */
    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute UserDTO userDTO){
        userService.createS(userDTO);
        return new ModelAndView("successful-registration");
    }
}
