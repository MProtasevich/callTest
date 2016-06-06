package by.mprotas.extractor.impl;

import by.mprotas.extractor.IPhoneExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PhoneExtractor implements IPhoneExtractor {
    // May be extracted as bean, but it's not necessary
    private static final String DEFAULT_AREA_CODE = "420";
    private static final int LOCAL_CODE_LENGTH = 9;
    private static final String DEFAULT_PREFIX = "00";

    @Autowired
    private Pattern phoneRedundantPartsPattern;

    @Autowired
    private Pattern prefixPattern;

    @Autowired
    private Pattern phoneFormatPattern;

    /**
     * Extracts phone to format 00YYY XXX XXX XXX, where YYY - area code and XXX XXX XXX - local part.
     * @param phone {@link String} which contains phone with some other symbols.
     * @return the {@link String} with phone in 00YYY XXX XXX XXX format.
     */
    @Override
    public String extractPhone(String phone) {
        String prefixlessPhone = prefixPattern.matcher(phone).replaceAll("");
        String contextFreePhone = phoneRedundantPartsPattern.matcher(prefixlessPhone).replaceAll("");
        if (contextFreePhone.length() == LOCAL_CODE_LENGTH) {
            contextFreePhone = DEFAULT_AREA_CODE + contextFreePhone;
        }
        if (contextFreePhone.length() == LOCAL_CODE_LENGTH + DEFAULT_AREA_CODE.length()) {
            contextFreePhone = DEFAULT_PREFIX + contextFreePhone;
        }
        return phoneFormatPattern.matcher(contextFreePhone).replaceAll("$1 $2 $3 $4");
    }
}
