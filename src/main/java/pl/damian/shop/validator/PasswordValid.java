package pl.damian.shop.validator;

import pl.damian.shop.validator.impl.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValid {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "Passwords should be the same";

}
