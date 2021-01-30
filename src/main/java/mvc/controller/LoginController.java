package mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    /**
     * Returns /login by GET method.
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
