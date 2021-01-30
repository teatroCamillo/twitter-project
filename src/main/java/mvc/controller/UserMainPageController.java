package mvc.controller;

import mvc.model.dto.UserDTO;
import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserMainPageController {

    @Autowired
    private UserService userService;

    /**
     * Returns /user-page by GET method.
     */
    @GetMapping("/user-page")
    public ModelAndView getUserPage(){
        return new ModelAndView("user-page");
    }

    /**
     * Returns list of users with the login search by POST method.
     */
    @PostMapping("/find-user-by-login")
    public ModelAndView findUserByLogin(String login) {
        List<UserDTO> listUsersByLogin = userService.findByLogin(login);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user-page");
        mv.addObject("listUsersByLogin", listUsersByLogin);
        return mv;
    }
}
