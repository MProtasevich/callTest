package by.mprotas.validator.annotation;

import by.mprotas.validator.BalancedParenthesesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BalancedParenthesesValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    int numberOfAreaCodeDigits() default 1;

    int numberOfLocalCodeDigits() default 7;

    String prefixPattern() default ".*";

    String availableSymbols() default " -";

    String message() default "{phone.does.not.match.the.pattern}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
