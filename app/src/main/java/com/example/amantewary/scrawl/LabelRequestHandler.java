package com.example.amantewary.scrawl;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.amantewary.scrawl.API.ILabelAPI;
import com.example.amantewary.scrawl.API.ILabelResponse;
import com.example.amantewary.scrawl.Handlers.LabelHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabelRequestHandler {

    private List<LabelHandler> labels;

    public Call<List<LabelHandler>> getLabel(final Context context, final Integer user_id,final @Nullable ILabelResponse callbacks){
        RetroFitInstance.getRetrofit().create(ILabelAPI.class)
                .getLabels(user_id)
                .enqueue(new Callback<List<LabelHandler>>() {
            @Override
            public void onResponse(Call<List<LabelHandler>> call, Response<List<LabelHandler>> response) {
                Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                labels = response.body();
                if(callbacks != null){
                    callbacks.onSuccess(labels);
                }
            }
            @Override
            public void onFailure(Call<List<LabelHandler>> call, Throwable t) {
                callbacks.onError(t);
            }
        });
        return RetroFitInstance.getRetrofit().create(ILabelAPI.class).getLabels(user_id);
    }
}
