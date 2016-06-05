package by.mprotas.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;

public class BalancedParenthesesValidatorTest {
    @Mock
    private ConstraintValidatorContext context;

    private BalancedParenthesesValidator validator;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        validator = new BalancedParenthesesValidator();
    }

    @Test
    public void testValidatorForBalancedParentheses() {
        assertTrue(validator.isValid("( )", context));
        assertTrue(validator.isValid("(())", context));
        assertTrue(validator.isValid("()()", context));
        assertTrue(validator.isValid("(())()", context));
    }

    @Test
    public void testForNonBalancedParantheses() {
        assertFalse(validator.isValid("(", context));
        assertFalse(validator.isValid(")(", context));
        assertFalse(validator.isValid("(()(", context));
    }
}
