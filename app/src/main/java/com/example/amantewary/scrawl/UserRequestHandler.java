package com.example.amantewary.scrawl;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.amantewary.scrawl.API.Users.ICheckUser;
import com.example.amantewary.scrawl.API.Users.IUserExistResponse;
import com.example.amantewary.scrawl.Handlers.UserClass;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class UserRequestHandler {

    private static final String TAG = "UserRequestHandler";

    private ProgressDialog dialog;

    public void checkIfUserExists(final Context context, String user_email, @Nullable final IUserExistResponse callbacks) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Looking for user...");
        dialog.show();

        ICheckUser service = RetroFitInstance.getRetrofit().create(ICheckUser.class);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), user_email);
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("email", body);
        Call<UserClass> call = service.checkIfUserExists(requestBodyMap);
        call.enqueue(new Callback<UserClass>() {
            @Override
            public void onResponse(Call<UserClass> call, retrofit2.Response<UserClass> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                    if (response.body().getError().equals("false")) {
                        callbacks.onSuccess(true);
                    } else {
                        callbacks.onSuccess(false);
                    }
                } else {
                    Log.e(TAG, "" + response.raw());
                }
            }

            @Override
            public void onFailure(Call<UserClass> call, Throwable t) {
                dialog.dismiss();
                callbacks.onError(t);
                Log.e(TAG, "checkIfUserExists.onFailure" + t.getMessage());
            }
        });

    }

}
