package mvc.repository;

import mvc.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findById(Integer id);

    <S extends User> S save(S s);

    void deleteUserById(Long id);
}
