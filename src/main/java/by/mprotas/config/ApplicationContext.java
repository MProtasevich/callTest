package by.mprotas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.ValidatorFactory;
import java.util.regex.Pattern;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.mprotas")
public class ApplicationContext {

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
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource resourceBundle = new ReloadableResourceBundleMessageSource();
        resourceBundle.setBasename("classpath:messages");
        return resourceBundle;
    }

    @Bean
    public ValidatorFactory validatorFactory() {
        LocalValidatorFactoryBean validatorFactory = new LocalValidatorFactoryBean();
        validatorFactory.setValidationMessageSource(messageSource());
        return validatorFactory;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }
}