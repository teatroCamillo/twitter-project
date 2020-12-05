package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class StartController {

    @GetMapping("/twitter-start")
    public String showStart(){
        return "twitter-start";
    }

//    @GetMapping(value = "/image")
//    public @ResponseBody byte[] getImage() throws IOException{
//        InputStream in = getClass()
//                .getResourceAsStream("templates/img/logo2.jpg");
//        return IOUtils.toByte???
//    }
}
