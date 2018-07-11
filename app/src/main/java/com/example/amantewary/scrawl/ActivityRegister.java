package com.example.amantewary.scrawl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.IRegisterUser;
import com.example.amantewary.scrawl.Handlers.LoginUserClass;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRegister extends AppCompatActivity {


    private TextInputEditText inputEmail, inputUsername, inputPassword;
    private Button registerButton;
    EmailPasswordValidation emailPasswordValidation;
    String TAG = ActivityRegister.class.getCanonicalName();
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailPasswordValidation = new EmailPasswordValidation();
        sessionManager = new SessionManager(getApplicationContext());
        initLayout();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

    }

    public void initLayout() {

        inputEmail = findViewById(R.id.editTextEmail);
        inputUsername = findViewById(R.id.editTextUserName);
        inputPassword = findViewById(R.id.editTextPassword);

        registerButton = findViewById(R.id.buttonRegister);
    }

    public void attemptRegister() {
        // check every field is valid

        String email = inputEmail.getText().toString();
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();


        if (validateFields(email, username, password)) {
            postcredentials(username, email, password);
        }
    }

    public boolean validateFields(String email, String username, String password) {

        if (email.isEmpty()) {
            inputEmail.setError("This should not be empty");
            return false;
        } else if (username.isEmpty()) {
            inputUsername.setError("This should not be empty");
            return false;
        } else if (password.isEmpty()) {
            inputPassword.setError("This should not be empty");
            return false;
        }else if(!emailPasswordValidation.isEmailValid(email)){
            inputEmail.setError("Enter a valid email");
            return false;
        }else if(!emailPasswordValidation.isPasswordValid(password)){
            inputPassword.setError("The password should be greater than 4");
            return false;
        }

        return true;
    }

    public void postcredentials(String username, String email, String password){

        IRegisterUser service = RetroFitInstance.getRetrofit().create(IRegisterUser.class);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody body2 = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody body3 = RequestBody.create(MediaType.parse("text/plain"), username);
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("email", body);
        requestBodyMap.put("password", body2);
        requestBodyMap.put("name", body3);
        Call<LoginUserClass> call = service.sendPostRegister(requestBodyMap);
        call.enqueue(new Callback<LoginUserClass>() {
            @Override
            public void onResponse(Call<LoginUserClass> call, Response<LoginUserClass> response) {
                if(response.isSuccessful()){
                    Log.e(TAG, response.body().getUsername());
                    sessionManager.createLoginSession(response.body().getUsername(), response.body().getEmail());
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginUserClass> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Looks like something is wrong!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
