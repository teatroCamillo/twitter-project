package mvc.repository;

import mvc.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SQLUserRepository extends UserRepository, JpaRepository<User, Integer> {
}
