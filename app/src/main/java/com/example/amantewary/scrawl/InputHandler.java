package com.example.amantewary.scrawl;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InputHandler {

    List<String> badWords = Arrays.asList("bitch", "dick");

    public boolean inputValidator(String title, String body, String link){

        if (!TextUtils.isEmpty(title)
                && !TextUtils.isEmpty(body)
                && (Patterns.WEB_URL.matcher(link).matches()
                || TextUtils.isEmpty(link))) {
            return true;
        }else{
            return false;
        }
    }

    public String inputCensor(String input){
        for (String word : badWords) {
            Pattern pattern = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
            input = pattern.matcher(input).replaceAll(new String(new char[word.length()]).replace('\0', '*'));
        }
        return input;
    }

    public void inputErrorHandling(EditText title, EditText body, EditText link){

        if (title.getText().toString().trim().matches("")) {
            title.setBackgroundResource(R.drawable.border_error);
            title.setError("Enter Note Title");
            return;
        } else {
            title.setBackgroundResource(R.drawable.border);
        }
        if (body.getText().toString().trim().matches("")) {
            body.setBackgroundResource(R.drawable.border_error);
            body.setError("Enter Note Body");
            return;
        } else {
            body.setBackgroundResource(R.drawable.border);
        }
        if (!Patterns.WEB_URL.matcher(link.getText().toString().trim()).matches()) {
            link.setError("Please Enter Valid URL");
            return;
        }
        else {
            link.setBackgroundResource(R.drawable.border);
        }

    }
}
