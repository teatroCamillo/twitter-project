package mvc.controller;

import mvc.model.dto.UserDTO;
import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserMainPageController {

    @Autowired
    private UserService userService;

    /**
     * Returns /home by GET method.
     */
    @GetMapping("/home")
    public ModelAndView getUserPage(){
        return new ModelAndView("home");
    }

    /**
     * Returns list of users on home page with the login search by POST method.
     */
    @PostMapping("/find-user-by-login")
    public ModelAndView findUserByLogin(String login) {
        List<UserDTO> listUsersByLogin = userService.findByLoginS(login);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        mv.addObject("listUsersByLogin", listUsersByLogin);
        return mv;
    }

    /**
     * Call whoFollowS method and return home page.
     */
    @PostMapping("/follow")
    public ModelAndView letsFollow(@RequestParam(name = "id") Integer id){
        userService.whoFollowS(id);
        return new ModelAndView("home");
    }
}
