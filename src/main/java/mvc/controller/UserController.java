package mvc.controller;

import mvc.model.dto.UserDTO;

import mvc.model.entity.User;
import mvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> findAllUsers() {
        logger.warn("Exposing all users!");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    ResponseEntity<UserDTO> findUserById(@PathVariable Integer id) {
        logger.warn("Exposing specific user!");
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        logger.info("Created new user!");
        User result = userService.create(userDTO);
        return ResponseEntity.created(URI.create("/" + result.getId())).build();
    }

    //    @GetMapping("/finduser")
//    public ModelAndView findUser() {
//        logger.warn("List the users");
//        return new ModelAndView("finduser", "finduser", new UserDTO());
//    }
}
