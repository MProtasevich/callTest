package by.mprotas.validator;

import by.mprotas.validator.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Used to validate whether {@link String} field specifies phone value.
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private Pattern redundantSymbolsPattern;
    private Pattern prefixPattern;
    private int numberOfAreaCodeDigits;
    private int numberOfLocalCodeDigits;

    @Override
    public void initialize(Phone phone) {
        prefixPattern = Pattern.compile(phone.prefixPattern());
        numberOfAreaCodeDigits = phone.numberOfAreaCodeDigits();
        numberOfLocalCodeDigits = phone.numberOfLocalCodeDigits();

        // In case, when someone puts alphabet characters
        redundantSymbolsPattern = Pattern.compile("[" + Pattern.quote(phone.availableSymbols()) + "]");
        // (\\d{1,3})(\\d{9})
    }

    /**
     * Validates whether the {@link String} contains exact number of digits
     * in local (mandatory) part and in area code (optional) part.
     * Omits the symbols, captured by {@link Phone prefixPattern} and all
     * the {@link Phone availableSymbols}.
     * To check balance of parentheses use {@link BalancedParenthesesValidator}
     * with {@link by.mprotas.validator.annotation.BalancedParentheses} annotation.
     *
     * @param phone {@link String} to validate.
     */
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (phone == null) {
            return true;
        }

        String prefixlessPhone = prefixPattern.matcher(phone).replaceFirst("");
        String prefixlessFormattedPhone = redundantSymbolsPattern.matcher(prefixlessPhone).replaceAll("");

        int countOfAreaCodeDigits = 0;
        int countOfLocalCodeDigits = 0;
        for (char c : new StringBuilder(prefixlessFormattedPhone).reverse().toString().toCharArray()) {
            if (Character.isDigit(c)) {
                if (countOfLocalCodeDigits < numberOfLocalCodeDigits) {
                    ++countOfLocalCodeDigits;
                } else {
                    ++countOfAreaCodeDigits;
                }

                if (countOfAreaCodeDigits > numberOfAreaCodeDigits) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return countOfLocalCodeDigits == numberOfLocalCodeDigits &&
                (countOfAreaCodeDigits == numberOfAreaCodeDigits || countOfAreaCodeDigits == 0);
    }
}
