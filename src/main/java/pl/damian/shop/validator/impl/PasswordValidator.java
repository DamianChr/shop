package pl.damian.shop.validator.impl;

import pl.damian.shop.domain.dto.UserDto;
import pl.damian.shop.validator.PasswordValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}
