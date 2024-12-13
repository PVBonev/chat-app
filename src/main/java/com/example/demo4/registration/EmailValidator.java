package com.example.demo4.registration;

import com.example.demo4.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+" +
                                                  "@[A-Z0-9.-]+" +
                                                  "\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean test(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);

        if (matcher.matches()) {
            logger.info("Valid email address");
            return true;
        } else {
            logger.info("Invalid email address");
            return false;
        }
    }
}
