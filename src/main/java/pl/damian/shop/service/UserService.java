package pl.damian.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.damian.shop.domain.dao.User;

public interface UserService {
    User saveUser(User user);

    User findUserById(Long id);

    User updateUser(User user, Long id);

    void deleteUserById (Long id);

    Page<User> getPage(Pageable pageable);

    User getCurrentUser();
}
