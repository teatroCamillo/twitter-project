package mvc.controller;

import mvc.model.dto.UserDTO;
import mvc.repository.UserRepository;
import mvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @GetMapping("/adduser")
//    public ModelAndView getAddNewUserView(){
//        return new ModelAndView("adduser","newUser", new UserDTO());
//    }
//
//    @PostMapping("/adduser")
//    public String addNewUser(@ModelAttribute UserDTO userDTO){
//        System.out.println(userDTO.getFirstName() + " " + userDTO.getLastName());
//        userService.addUser(userDTO);
//        return "index";
//    }

//    @GetMapping("/users")
//    public ModelAndView getAllUsers(){
//        logger.warn("Exposing all users!");
//        List<UserDTO> userDTOList = userService.getAllUsers();
//        return new ModelAndView("users","userList", userDTOList);
//    }

    @GetMapping("/finduser")
    public ModelAndView findUser(){
        logger.warn("List the users");
        return new ModelAndView("finduser","finduser",new UserDTO());
    }

    @GetMapping("/finduserbylastname")
    public ModelAndView findUserByLastName(@ModelAttribute UserDTO userDTO){
        logger.warn("Find a user by a last name");
        List<UserDTO> userDTOList = userService.findUserByLastName(userDTO.getLastName());
        return  new ModelAndView("users","userList",userDTOList);
    }

}
