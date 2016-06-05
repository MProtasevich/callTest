package by.mprotas.config;

import by.mprotas.extractor.IPhoneExtractor;
import by.mprotas.extractor.impl.PhoneExtractor;
import org.springframework.context.annotation.Bean;

import java.util.regex.Pattern;

public class TestApplicationContext {
    @Bean
    public Pattern phoneRedundantPartsPattern() {
        return Pattern.compile("[ \\-\\+\\(\\)]");
    }

    @Bean
    public Pattern prefixPattern() {
        return Pattern.compile("^(\\+|00)");
    }

    @Bean
    public Pattern phoneFormatPattern() {
        return Pattern.compile("(00\\d{3})(\\d{3})(\\d{3})(\\d{3})");
    }

    @Bean
    public IPhoneExtractor phoneExtractor() {
        return new PhoneExtractor();
    }
}
