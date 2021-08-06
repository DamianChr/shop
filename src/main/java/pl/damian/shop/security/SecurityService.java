package pl.damian.shop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.damian.shop.service.UserService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;

    public boolean hasAccessToUser(Long id) {
        try {
            return userService.getCurrentUser().getId().equals(id);
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

}
