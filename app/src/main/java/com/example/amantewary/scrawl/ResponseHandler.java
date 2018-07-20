package com.example.amantewary.scrawl;

import android.content.Context;
import android.util.Log;

import com.example.amantewary.scrawl.API.IHandleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResponseHandler {

    public ResponseHandler() {
    }

    public static <T> void ResponseFromGetMethod(Call<List<T>> call, final IHandleResponse callbacks, final Context context) {

        call.enqueue(new Callback<List<T>>() {
            @Override
            public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                List<T> responseList = response.body();
                if (callbacks != null) {
                    callbacks.onSuccess(responseList);
                }
            }

            @Override
            public void onFailure(Call<List<T>> call, Throwable t) {
                callbacks.onError(t);
            }
        });

    }
}
