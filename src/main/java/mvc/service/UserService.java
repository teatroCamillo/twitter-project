package mvc.service;

import mvc.model.dto.UserDTO;
import mvc.model.entity.User;
import mvc.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<UserDTO> getAllUsersS() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Returns list of mapped users by search @param login.
     */
    public List<UserDTO> findByLoginS(String login) {
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
    public User createS(UserDTO userDTO){
        userDTO.setRole("USER");
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }

    /**
     * Return actual logged User userId.
     */
    public Integer getCurrentUserIdByQueryS(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.getActualUserId(auth.getName(), auth.getCredentials().toString());
    }

    /**
     * Saves record in db who is following and who is follower.
     */
    public void whoFollowS(Integer followingId){
        userRepository.whoFollow(followingId, getCurrentUserIdByQueryS());
    }

}
