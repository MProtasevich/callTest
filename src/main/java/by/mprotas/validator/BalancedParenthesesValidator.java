package by.mprotas.validator;

import by.mprotas.validator.annotation.BalancedParentheses;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BalancedParenthesesValidator implements ConstraintValidator<BalancedParentheses, String> {
    @Override
    public void initialize(BalancedParentheses parenthesesBalanced) {
    }

    @Override
    public boolean isValid(String stringWithParentheses, ConstraintValidatorContext constraintValidatorContext) {
        if (stringWithParentheses == null) {
            return true;
        }
        int countOfOpenedButNotClosedParentheses = 0;

        for (char c : stringWithParentheses.toCharArray()) {
            if (c == '(') {
                ++countOfOpenedButNotClosedParentheses;
            } else if (c == ')'){
                --countOfOpenedButNotClosedParentheses;
            }

            if (countOfOpenedButNotClosedParentheses < 0) {
                return false;
            }
        }

        return countOfOpenedButNotClosedParentheses == 0;
    }
}
