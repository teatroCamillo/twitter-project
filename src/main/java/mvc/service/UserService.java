package mvc.service;

import mvc.model.dto.UserDTO;
import mvc.model.entity.User;
import mvc.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    /**
     * Returns the entire list of mapped users from User to UserDTO.
     */
    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Returns list of mapped users by search @param login.
     */
    public List<UserDTO> findByLogin(String login) {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> user.getLogin().contains(login))
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Sets role for user, maps it to User & stores in database.
     */
    public User create(UserDTO userDTO){
        userDTO.setRole("USER");
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }
}
