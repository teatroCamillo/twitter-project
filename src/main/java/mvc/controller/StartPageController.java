package mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StartPageController {

    /**
     * Returns /start-page by GET method.
     */
    @GetMapping("/")
    public ModelAndView getStart() {
        return new ModelAndView("start-page");
    }
}
