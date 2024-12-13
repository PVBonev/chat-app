package com.example.demo4.registration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    private final EmailValidator emailValidator = new EmailValidator();

    @Test
    void testValidEmail() {
        assertTrue(emailValidator.test("test@gmail.com"));
        assertTrue(emailValidator.test("a@b.cd"));
        assertTrue(emailValidator.test("user.na_me+%t+p@abv.bg"));
        assertTrue(emailValidator.test("user_name@example.co.uk"));
    }

    @Test
    void testInvalidEmail() {
        assertFalse(emailValidator.test("address"));
        assertFalse(emailValidator.test("@noname.com"));
        assertFalse(emailValidator.test("username@.com"));
        assertFalse(emailValidator.test("username@.com."));
        assertFalse(emailValidator.test("username@%.com"));
        assertFalse(emailValidator.test("username@gmail.s"));
        assertFalse(emailValidator.test("username@.loooooong"));
    }
}