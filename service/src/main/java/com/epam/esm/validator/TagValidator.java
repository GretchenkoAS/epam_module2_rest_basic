package com.epam.esm.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TagValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("\\w{3,30}");

    public TagValidator() {
    }

    public boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        Matcher matcher = NAME_REGEX.matcher(name);
        return matcher.matches();
    }
}
