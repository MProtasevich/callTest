package by.mprotas.validator.annotation;

import by.mprotas.validator.BalancedParenthesesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BalancedParenthesesValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BalancedParentheses {
    String message() default "{parentheses.are.not.balanced}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
