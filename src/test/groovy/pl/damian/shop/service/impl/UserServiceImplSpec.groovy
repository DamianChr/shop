package pl.damian.shop.service.impl

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import pl.damian.shop.domain.dao.Role
import pl.damian.shop.domain.dao.User
import pl.damian.shop.repository.RoleRepository
import pl.damian.shop.repository.UserRepository
import pl.damian.shop.service.Impl.UserServiceImpl
import spock.lang.Specification

class UserServiceImplSpec extends Specification {

    def userService
    def roleRepository = Mock(RoleRepository)
    def userRepository = Mock(UserRepository)
    def passwordEncoder = Mock(PasswordEncoder)
    def authentication = Mock(Authentication)
    def securityContext = Mock(SecurityContext)

    def setup() {
        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder)
    }

    def 'test saveUser'() {
        given:
        def user = new User(password: 'password')

        when:
        userService.saveUser(user)

        then:
        1 * roleRepository.findByName("ROLE_USER") >> Optional.of(new Role())
        1 * passwordEncoder.encode(user.password)
        1 * userRepository.save(user)
        0 * _//po funkcji save nic więcej sie nie wykonało
    }

    def 'test should get user by id'() {
        when:
        userService.findUserById(2l)

        then:
        1 * userRepository.getOne(2l)
        0 * _
    }

    def 'test updateUser'() {
        given:
        def user = new User(firstName: 'user', lastName: 'user', email: 'user1@wp.pl', mobile: 555555)
        userRepository.getOne(2l) >> new User(id: 2l, firstName: "name", lastName: 'lastName', email: 'email@wp.pl', mobile: 12345)

        when:
        def result = userService.updateUser(user, 2l)

        then:
        result.id == 2l
        result.firstName == user.firstName
        result.lastName == user.lastName
        result.email == user.email
        result.mobile == user.mobile

    }

    def 'test getCurrentUser'() {

        given:
        securityContext.getAuthentication() >> authentication
        SecurityContextHolder.setContext(securityContext)
        SecurityContextHolder.getContext().getAuthentication().getName() >> 'email'
        userRepository.findByEmail('email') >> Optional.of(new User())

        when:
        def result = userService.getCurrentUser()

        then:
        result != null


    }


}
