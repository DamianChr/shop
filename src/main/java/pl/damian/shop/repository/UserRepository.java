package pl.damian.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import pl.damian.shop.domain.dao.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, RevisionRepository<User,Long,Long> {

    Optional<User> findByEmail(String email);

}
