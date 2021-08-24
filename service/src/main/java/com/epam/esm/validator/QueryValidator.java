package com.epam.esm.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class QueryValidator {
    private static final Pattern TAG_NAME_REGEX = Pattern.compile("\\w{3,30}");
    private static final Pattern CONTAINS_REGEX = Pattern.compile("\\w{3,80}");
    private static final Pattern SORT_NAME_REGEX = Pattern.compile("ASC|DESC");

    public QueryValidator() {
    }

    public boolean isValidTagName(String tagName) {
        if (tagName == null || tagName.isBlank()) {
            return false;
        }
        Matcher matcher = TAG_NAME_REGEX.matcher(tagName);
        return matcher.matches();
    }

    public boolean isValidContains(String contains) {
        if (contains == null || contains.isBlank()) {
            return false;
        }
        Matcher matcher = CONTAINS_REGEX.matcher(contains);
        return matcher.matches();
    }

    public boolean isValidSortName(String sortName) {
        if (sortName == null || sortName.isBlank()) {
            return false;
        }
        Matcher matcher = SORT_NAME_REGEX.matcher(sortName);
        return matcher.matches();
    }
}
