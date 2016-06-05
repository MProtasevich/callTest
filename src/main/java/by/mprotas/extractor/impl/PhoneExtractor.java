package by.mprotas.extractor.impl;

import by.mprotas.extractor.IPhoneExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PhoneExtractor implements IPhoneExtractor {
    @Autowired
    private Pattern phoneRedundantPartsPattern;

    @Autowired
    private Pattern prefixPattern;

    public String extractPhone(String phone) {
        String prefixlessPhone = prefixPattern.matcher(phone).replaceAll("");
        return phoneRedundantPartsPattern.matcher(prefixlessPhone).replaceAll("");
    }
}
