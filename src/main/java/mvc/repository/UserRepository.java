package mvc.repository;

import mvc.model.entity.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository{

    List<User> findUserByLastName(@Param("lastName") String lastName);

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User user);

    void deleteUserById(Long id);
}
