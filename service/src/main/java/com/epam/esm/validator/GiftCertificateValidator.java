package com.epam.esm.validator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GiftCertificateValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("\\w{3,30}");
    private static final Pattern DESCRIPTION_REGEX = Pattern.compile("\\w{3,80}");
    private final static BigDecimal MIN_PRICE_VALUE = new BigDecimal(1.00);
    private final static BigDecimal MAX_PRICE_VALUE = new BigDecimal(10000L);
    private final static Integer MIN_DURATION_VALUE = 1;
    private final static Integer MAX_DURATION_VALUE = 5000;

    public GiftCertificateValidator() {
    }

    public boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        Matcher matcher = NAME_REGEX.matcher(name);
        return matcher.matches();
    }

    public boolean isValidDescription(String description) {
        if (description == null || description.isBlank()) {
            return false;
        }
        Matcher matcher = DESCRIPTION_REGEX.matcher(description);
        return matcher.matches();
    }

    public boolean isValidPrice(BigDecimal price) {
        if (price == null) {
            return false;
        }
        return MIN_PRICE_VALUE.compareTo(price) <= 0
                && MAX_PRICE_VALUE.compareTo(price) > 0;
    }

    public boolean isValidDuration(Integer duration) {
        if (duration == null) {
            return false;
        }
        return MIN_DURATION_VALUE.compareTo(duration) <= 0
                && MAX_DURATION_VALUE.compareTo(duration) > 0;
    }
}
