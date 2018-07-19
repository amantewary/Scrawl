package com.example.amantewary.scrawl;

import android.content.Context;
import android.util.Log;

import com.example.amantewary.scrawl.API.IHandleResponse;
import com.example.amantewary.scrawl.Handlers.DataHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ResponseHandler {

    public ResponseHandler() {
    }

    public static  void ResponseFromGetMethod(Call<List<DataHandler>> call, final IHandleResponse callbacks, final Context context){

        call.enqueue(new Callback<List<DataHandler>>() {
            @Override
            public void onResponse(Call<List<DataHandler>> call, Response<List<DataHandler>> response) {
                Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                List<DataHandler>responseList = response.body();
                if (callbacks != null) {
                    callbacks.onSuccess(responseList);
                }
            }

            @Override
            public void onFailure(Call<List<DataHandler>> call, Throwable t) {
                callbacks.onError(t);
            }
        });

    }
}
