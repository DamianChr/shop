package pl.damian.shop.controller.history;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.history.Revision;
import org.springframework.web.bind.annotation.*;
import pl.damian.shop.domain.dao.User;
import pl.damian.shop.repository.UserRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history/users")
public class UserHistoryController {
    private final UserRepository userRepository;

    @GetMapping
    public Page<Revision<Long, User>> getUserHistory(@RequestParam Long id,@RequestParam int page,@RequestParam int size) {

        return userRepository.findRevisions(id, PageRequest.of(page, size));

    }

}
