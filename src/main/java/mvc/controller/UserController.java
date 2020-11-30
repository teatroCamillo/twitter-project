package mvc.controller;

import mvc.model.dto.UserDTO;
<<<<<<< HEAD
=======
import mvc.model.entity.User;
>>>>>>> Added controllers & resources.
import mvc.repository.UserRepository;
import mvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
>>>>>>> Added controllers & resources.
import java.util.List;

@RestController
public class UserController {
<<<<<<< HEAD

=======
>>>>>>> Added controllers & resources.
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

<<<<<<< HEAD

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

=======
    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> findAllUsers(){
        logger.warn("Exposing all users!");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    ResponseEntity<UserDTO> findUserById(@PathVariable Integer id){
        logger.warn("Exposing specific user!");
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        logger.info("Created new user!");
        User result = userService.create(userDTO);
        return ResponseEntity.created(URI.create("/" + result.getId())).build();
    }
>>>>>>> Added controllers & resources.
}
