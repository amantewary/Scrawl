package com.example.amantewary.scrawl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;


@RunWith(RobolectricTestRunner.class)
public class EmailValidationTestR {

    EmailPasswordValidation emailPasswordValidation;
    @Before
    public void init(){
        emailPasswordValidation = new EmailPasswordValidation();
    }

    @Test
    public void emailValidator_EmptyEmailValidation_ReturnsFalse(){
        assertEquals(emailPasswordValidation.isEmailValid(""), false);
    }

    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertEquals(emailPasswordValidation.isEmailValid("noname@gmail.com"), true);
    }

    @Test()
    public void emailValidator_CorrectEmailIncorrect_ReturnsFalse() {
        assertEquals(emailPasswordValidation.isEmailValid("dfdsf@,,sdf"), false);
    }

    @Test(expected=NullPointerException.class)
    public void emailValidator_NullEmailSimple_ReturnsFalse() {
        assertEquals(emailPasswordValidation.isEmailValid(null), false);
    }

}
