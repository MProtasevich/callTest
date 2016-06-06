package by.mprotas.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import by.mprotas.validator.annotation.Phone;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;

public class PhoneValidatorTest {
    private PhoneValidator phoneValidator = new PhoneValidator();
    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        phoneValidator.initialize(new Phone() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return this.getClass();
            }

            @Override
            public int numberOfAreaCodeDigits() {
                return 3;
            }

            @Override
            public int numberOfLocalCodeDigits() {
                return 9;
            }

            @Override
            public String prefixPattern() {
                return "^(\\+|00)?";
            }

            @Override
            public String availableSymbols() {
                return " -()";
            }

            @Override
            public String message() {
                return null;
            }

            @Override
            public Class<?>[] groups() {
                return new Class<?>[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }
        });
    }

    @Test
    public void testPhoneValidatorPassCases() {
        assertTrue(phoneValidator.isValid("+(420) 111 222 333", constraintValidatorContext));
        assertTrue(phoneValidator.isValid("+(420)-111222333", constraintValidatorContext));
        assertTrue(phoneValidator.isValid("+420111222333", constraintValidatorContext));
        assertTrue(phoneValidator.isValid("420111222333", constraintValidatorContext));
        assertTrue(phoneValidator.isValid("00420111222333", constraintValidatorContext));
        assertTrue(phoneValidator.isValid("(111) 222 (333)", constraintValidatorContext));
        assertTrue(phoneValidator.isValid("123456789", constraintValidatorContext));
    }
    
    @Test
    public void testPhoneValidatorFailCases() {
//        assertFalse(phoneValidator.isValid("+(420 111 222 333", constraintValidatorContext));
        assertFalse(phoneValidator.isValid("+(420)-1112223331", constraintValidatorContext));
        assertFalse(phoneValidator.isValid("1+20111222333", constraintValidatorContext));
        assertFalse(phoneValidator.isValid("0420111222333", constraintValidatorContext));
//        assertFalse(phoneValidator.isValid("111) 222 (333)", constraintValidatorContext));
        assertFalse(phoneValidator.isValid("23456789", constraintValidatorContext));
    }
}
