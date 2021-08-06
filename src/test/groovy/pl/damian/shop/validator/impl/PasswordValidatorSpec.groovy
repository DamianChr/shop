package pl.damian.shop.validator.impl

import pl.damian.shop.domain.dto.UserDto
import spock.lang.Specification


class PasswordValidatorSpec extends Specification {

    def passwordValidator = new PasswordValidator()

    def 'test password validator'() {
        given:
        def userDto = new UserDto(password: password, confirmPassword: confirmPassword)

        when:
        def result = passwordValidator.isValid(userDto, null)

        then:
        result == expected

        where:
        password | confirmPassword || expected
        'abcd'   | 'bca'           || false
        'abcd'   | 'abcd'          || true
    }

}
