package by.mprotas.extractor;

import static org.junit.Assert.assertEquals;

import by.mprotas.config.TestApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = TestApplicationContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PhoneExtractorTest {
    @Autowired
    private IPhoneExtractor phoneExtractor;

    @Test
    public void testPhoneExtractor() {
        assertEquals("00420 111 222 333", phoneExtractor.extractPhone("+(420) 111 222 333"));
        assertEquals("00420 111 222 333", phoneExtractor.extractPhone("+(420)-111222333"));
        assertEquals("00420 111 222 333", phoneExtractor.extractPhone("+420111222333"));
        assertEquals("00420 111 222 333", phoneExtractor.extractPhone("00420111222333"));
        assertEquals("00420 111 222 333", phoneExtractor.extractPhone("(111) 222 (333)"));
        assertEquals("00420 123 456 789", phoneExtractor.extractPhone("123456789"));
    }
}
