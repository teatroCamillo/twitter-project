package mvc.service;

import mvc.model.dto.UserDTO;
import mvc.model.entity.User;
import mvc.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserService(final ModelMapper modelMapper, final UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

//    public void addUser(UserDTO userDTO){
//        User user = modelMapper.map(userDTO, User.class);
//        userRepository.save(user);
//    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> findUserByLastName(String lastName){
        return userRepository
                .findUserByLastName(lastName)
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Integer id){
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDTO.class));
    }

    public User create(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }
}
