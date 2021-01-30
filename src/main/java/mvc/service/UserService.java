package mvc.service;

import mvc.model.dto.UserDTO;
import mvc.model.entity.User;
import mvc.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserService(final ModelMapper modelMapper, final UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> findByLogin(String login) {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Integer id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDTO.class));
    }

    public User create(UserDTO userDTO){
        userDTO.setRole("USER");
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }

     public void deleteUserByHisID(Long id){
        String sqlDelete = "DELETE FROM users WHERE id=?";
        jdbcTemplate.update(sqlDelete,1L);
         System.out.println("User deleted with ID = " + id);
     }

}
