package com.example.amantewary.scrawl;

import android.app.Activity;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class InputHandlerTest {

    private InputHandler SUT;
    private Activity activity;
    private EditText testTitle;
    private EditText testBody;
    private EditText testUrl;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(AddNotesActivity.class);
        SUT = new InputHandler(activity);
        testTitle = activity.findViewById(R.id.et_title);
        testBody = activity.findViewById(R.id.et_content);
        testUrl = activity.findViewById(R.id.et_link);
    }

    @Test
    public void inputValidator_emptyString_falseReturned() {
        boolean result = SUT.inputValidator("","","");
        assertThat(result, is(false));
    }

    @Test
    public void inputValidator_emptyTitle_falseReturned() {
        boolean result = SUT.inputValidator("","BODY","www.test.com");
        assertThat(result, is(false));
    }

    @Test
    public void inputValidator_emptyBody_falseReturned() {
        boolean result = SUT.inputValidator("TITLE","","www.test.com");
        assertThat(result, is(false));
    }

    @Test
    public void inputValidator_emptyUrl_trueReturned() {
        boolean result = SUT.inputValidator("TITLE","BODY","");
        assertThat(result, is(true));
    }

    @Test
    public void inputValidator_wrongUrlPattern_falseReturned() {
        boolean result = SUT.inputValidator("TITLE","BODY","URL");
        assertThat(result, is(false));
    }


    @Test
    public void inputValidator_correctInput_trueReturned() {
        boolean result = SUT.inputValidator("TITLE","BODY","www.test.com");
        assertThat(result, is(true));
    }

    @Test
    public void inputCensor_emptyString_emptyStringReturned() {
        String result = SUT.inputCensor("");
        assertThat(result, is(""));
    }

    @Test
    public void inputCensor_singleCharacter_singleCharacterReturned() {
        String result = SUT.inputCensor("a");
        assertThat(result, is("a"));
    }

    @Test
    public void inputCensor_goodWordsString_emptyStringReturned() {
        String result = SUT.inputCensor("happy");
        assertThat(result, is("happy"));
    }

    @Test
    public void inputCensor_badWordsString_emptyStringReturned() {
        String result = SUT.inputCensor("hell");
        assertThat(result, is("****"));
    }

    @Test
    public void inputErrorHandler_validInput_noErrorReturned() throws NullPointerException{
        testTitle.setText("TITLE");
        testBody.setText("BODY");
        testUrl.setText("www.test.com");
        SUT.inputErrorHandling(testTitle, testBody, testUrl);
        assertThat(testTitle.getError(), is(nullValue()));
        assertThat(testBody.getError(), is(nullValue()));
        assertThat(testUrl.getError(),is(nullValue()));
    }

    @Test
    public void inputErrorHandler_emptyString_errorReturned() {
        testTitle.setText("");
        testBody.setText("");
        testUrl.setText("");
        SUT.inputErrorHandling(testTitle, testBody, testUrl);
        String result = testTitle.getError().toString();
        assertThat(result, is("Enter Note Title"));
    }

    @Test
    public void inputErrorHandler_emptyBodyAndUrl_errorReturned() {
        testTitle.setText("TITLE");
        testBody.setText("");
        testUrl.setText("");
        SUT.inputErrorHandling(testTitle, testBody, testUrl);
        String result = testBody.getError().toString();
        assertThat(result, is("Enter Note Body"));
    }

    @Test
    public void inputErrorHandler_emptyUrl_errorReturned() {
        testTitle.setText("TITLE");
        testBody.setText("BODY");
        testUrl.setText("");
        SUT.inputErrorHandling(testTitle, testBody, testUrl);
        String result = testUrl.getError().toString();
        assertThat(result, is("Please Enter Valid URL"));
    }

    @Test
    public void inputErrorHandler_invalidPatternUrl_errorReturned() {
        testTitle.setText("TITLE");
        testBody.setText("BODY");
        testUrl.setText("URL");
        SUT.inputErrorHandling(testTitle, testBody, testUrl);
        String result = testUrl.getError().toString();
        assertThat(result, is("Please Enter Valid URL"));
    }

    @Test
    public void inputErrorHandler_singleCharacter_sameCharacterReturned() throws NullPointerException{
        testTitle.setText("a");
        testBody.setText("a");
        testUrl.setText("a");
        SUT.inputErrorHandling(testTitle, testBody, testUrl);
        assertThat(testTitle.getError(), is(nullValue()));
        assertThat(testBody.getError(), is(nullValue()));
        assertThat(testUrl.getError().toString(),is("Please Enter Valid URL"));
    }
}