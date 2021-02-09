package mvc.repository;

import mvc.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    <S extends User> S save(S s);

    @Query(value = "select id from users where login = :login and password = :password", nativeQuery = true)
    Integer getActualUserId(@Param("login") String login, @Param("password") String password);

    @Modifying
    @Query(value = "insert into users_followers value (:following, :follower)", nativeQuery = true)
    @Transactional
    void whoFollow(@Param("following") Integer following, @Param("follower") Integer follower);
}
