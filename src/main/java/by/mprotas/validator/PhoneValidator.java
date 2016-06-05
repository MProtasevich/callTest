package by.mprotas.validator;

import by.mprotas.validator.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private Pattern redundantSymbolsPattern;
    private Pattern prefixPattern;
    private char[] availableSymbols;
    private int numberOfAreaCodeDigits;
    private int numberOfLocalCodeDigits;

    @Override
    public void initialize(Phone phone) {
        prefixPattern = Pattern.compile(phone.prefixPattern());
        availableSymbols = phone.availableSymbols().toCharArray();
        Arrays.sort(availableSymbols);
        numberOfAreaCodeDigits = phone.numberOfAreaCodeDigits();
        numberOfLocalCodeDigits = phone.numberOfLocalCodeDigits();

        // In case, when someone puts alphabet characters
        redundantSymbolsPattern = Pattern.compile(Pattern.quote(phone.availableSymbols()));
        // (\\d{1,3})(\\d{9})
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        Matcher prefixMatcher = prefixPattern.matcher(phone);
        if (!prefixMatcher.matches()) {
            return false;
        }
        String prefixlessPhone = prefixMatcher.replaceFirst("");
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
