package by.mprotas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.regex.Pattern;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.mprotas")
public class ApplicationContext {

    @Bean
    public Pattern phoneRedundantPartsPattern() {
        return Pattern.compile(" \\-\\+\\(\\)");
    }
}