package pl.damian.shop.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.damian.shop.domain.dao.User;
import pl.damian.shop.repository.RoleRepository;
import pl.damian.shop.repository.UserRepository;
import pl.damian.shop.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(Collections.singletonList(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword())); //hashujemy haslo
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    @Transactional
    public User updateUser(User user, Long id) {
        User userDb = findUserById(id);
        userDb.setEmail(user.getEmail());
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        userDb.setMobile(user.getMobile());

        return userDb;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(()->new EntityNotFoundException("User not loged"));
    }


}
