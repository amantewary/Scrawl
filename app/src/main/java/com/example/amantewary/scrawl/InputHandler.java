package com.example.amantewary.scrawl;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.regex.Pattern;

public class InputHandler extends Observable {

    private static final String TAG = "InputHandler";
    List<String> badWords = new ArrayList<>();
    private Context context;

    public InputHandler(Context context) {
        this.context = context;
        readTxt();
    }

    public boolean inputValidator(String title, String body, String link) {

        if (!TextUtils.isEmpty(title)
                && !TextUtils.isEmpty(body)
                && (Patterns.WEB_URL.matcher(link).matches()
                || TextUtils.isEmpty(link))) {
            return true;
        } else {
            return false;
        }
    }

    public String inputCensor(String input) {
        String temp = input;
        Log.e("Temp", input);
        for (String word : badWords) {
            Pattern pattern = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
            input = pattern.matcher(input).replaceAll(new String(new char[word.length()]).replace('\0', '*'));
        }
        Log.e("input", input);
        if (!temp.equals(input)) {
            setChanged();
            notifyObservers();
        }
        badWords.clear();
        return input;
    }

    public void inputErrorHandling(EditText title, EditText body, EditText link) {

        if (title.getText().toString().trim().matches("")) {
            title.setBackgroundResource(R.drawable.border_error);
            title.setError("Enter Note Title");
            Log.d(TAG,"Note Title Not Entered");
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
        } else {
            link.setBackgroundResource(R.drawable.border);
        }

    }

    private void readTxt() {
        try {
            InputStreamReader inputStream = new InputStreamReader(context.getAssets().open("swearwords.txt"));
            BufferedReader reader = new BufferedReader(inputStream);
            String mLine = reader.readLine();
            while (mLine != null) {

                badWords.add(mLine);
                mLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            Log.e("TAG", "" + e);
        }

    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();

    }

    public void doRealTimeLanguageCheck(final EditText editText) {
        try {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() != 0) {
                        inputCensor(editText.getText().toString().trim());
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }
    }
}
