package com.example.amantewary.scrawl;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.Labels.ICreateLabel;
import com.example.amantewary.scrawl.API.Labels.IDeleteLabel;
import com.example.amantewary.scrawl.API.Labels.IGetLabels;
import com.example.amantewary.scrawl.API.Labels.ILabelResponse;
import com.example.amantewary.scrawl.API.Labels.IUpdateLabel;
import com.example.amantewary.scrawl.Handlers.LabelHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabelRequestHandler {

    private static final String TAG = "LabelRequestHandler";
    private List<LabelHandler> labels;

    public void getLabel(final Context context, final Integer user_id, final @Nullable ILabelResponse callbacks) {
        IGetLabels labelAPI = RetroFitInstance.getRetrofit().create(IGetLabels.class);
        Call<List<LabelHandler>> call = labelAPI.getLabels(user_id);
        try {
            call.enqueue(new Callback<List<LabelHandler>>() {
                @Override
                public void onResponse(Call<List<LabelHandler>> call, Response<List<LabelHandler>> response) {
                    Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                    labels = response.body();
                    if (callbacks != null) {
                        callbacks.onSuccess(labels);
                    }
                }

                @Override
                public void onFailure(Call<List<LabelHandler>> call, Throwable t) {
                    callbacks.onError(t);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }

    }

    public void createLabel(LabelHandler labelHandler, final Context context) {
        try {
            RetroFitInstance.getRetrofit().create(ICreateLabel.class)
                    .createLabel(labelHandler)
                    .enqueue(new Callback<LabelHandler>() {
                        @Override
                        public void onResponse(Call<LabelHandler> call, Response<LabelHandler> response) {
                            Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                            Log.d(context.getClass().getSimpleName(), "onResponse: Received Information: " + response.body().toString());
                            Toast.makeText(context, "Label Created", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<LabelHandler> call, Throwable t) {
                            Log.e(context.getClass().getSimpleName(), "onFailure: Something Went Wrong: " + t.getMessage());
                            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }
    }

    public void editLabel(LabelHandler labelHandler, final Context context) {
        try {
            RetroFitInstance.getRetrofit().create(IUpdateLabel.class)
                    .updateLabel(labelHandler)
                    .enqueue(new Callback<LabelHandler>() {
                        @Override
                        public void onResponse(Call<LabelHandler> call, Response<LabelHandler> response) {
                            Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                            Log.d(context.getClass().getSimpleName(), "onResponse: Received Information: " + response.body().toString());
                            Toast.makeText(context, "Note Edited", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<LabelHandler> call, Throwable t) {
                            Log.e(context.getClass().getSimpleName(), "onFailure: Something Went Wrong: " + t.getMessage());
                            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }
    }

    public void deleteLabel(final LabelHandler labelHandler, final Context context) {
        try {
            RetroFitInstance.getRetrofit().create(IDeleteLabel.class)
                    .deleteLabel(labelHandler)
                    .enqueue(new Callback<LabelHandler>() {
                        @Override
                        public void onResponse(Call<LabelHandler> call, Response<LabelHandler> response) {
                            Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                            Log.d(context.getClass().getSimpleName(), "onResponse: Received Information: " + response.body().toString());
                            Toast.makeText(context, "Label Deleted", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<LabelHandler> call, Throwable t) {
                            Log.e(context.getClass().getSimpleName(), "onFailure: Something Went Wrong: " + t.getMessage());
                            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }
    }
}
