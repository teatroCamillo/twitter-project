package mvc.repository;

import mvc.model.entity.User;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    List<User> findUserByLastName(@Param("lastName") String lastName);

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User user);
}
