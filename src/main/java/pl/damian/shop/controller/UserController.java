package pl.damian.shop.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.damian.shop.domain.dto.UserDto;
import pl.damian.shop.mapper.UserMapper;
import pl.damian.shop.service.UserService;
import pl.damian.shop.validator.group.UserCreate;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    @ApiOperation("get user by id")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable @ApiParam("user id") Long id) {
        return userMapper.daoToDto(userService.findUserById(id));
    }

    @Validated(UserCreate.class)
    @PostMapping
    public UserDto saveUser(@RequestBody @Valid UserDto user) {
        return userMapper.daoToDto(userService.saveUser(userMapper.dtoToDao(user)));
    }

    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody @Valid UserDto user, @PathVariable Long id) {
        return userMapper.daoToDto(userService.updateUser(userMapper.dtoToDao(user), id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<UserDto> pageUser(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size)).map(userMapper::daoToDto);
    }


}