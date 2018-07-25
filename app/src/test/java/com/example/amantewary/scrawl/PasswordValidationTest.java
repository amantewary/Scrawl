package com.example.amantewary.scrawl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordValidationTest {

    private EmailPasswordValidation emailPasswordValidation;
    @Before
    public void initClass(){
        emailPasswordValidation = new EmailPasswordValidation();
    }

    @Test(expected = NullPointerException.class)
    public void passValidation_withNullPassword(){
        assertEquals(emailPasswordValidation.isPasswordValid(null), false);
    }

    @Test()
    public void passValidation_withInvalidPassword(){
        assertEquals(emailPasswordValidation.isPasswordValid("12"), true);
    }

    @Test()
    public void passValidation_withValidPassword(){
        assertEquals(emailPasswordValidation.isPasswordValid("123456"), false);
    }

}
