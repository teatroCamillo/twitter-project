package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {

    @GetMapping("/start")
    public String showStart2(){
        return "start";
    }

//    @GetMapping("/start")
//    public ModelAndView showStart(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("start");
//        return modelAndView;
//    }

}
