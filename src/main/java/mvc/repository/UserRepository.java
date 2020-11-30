package mvc.repository;

import mvc.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findUserByLastName(@Param("lastName") String lastName);
}
