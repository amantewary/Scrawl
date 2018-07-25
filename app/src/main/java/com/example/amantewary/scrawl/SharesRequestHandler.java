package com.example.amantewary.scrawl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.Shares.ICreateShare;
import com.example.amantewary.scrawl.API.Shares.IDeleteShare;
import com.example.amantewary.scrawl.Handlers.ShareHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharesRequestHandler {
    private static final String TAG = "SharesRequestHandler";
    private List<ShareHandler> shares;
    private ProgressDialog dialog;
    private SessionManager sessionManager;

    public void createShare(ShareHandler shareHandler, final Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Sharing...");
        dialog.show();

        try{
            ICreateShare shareAPI = RetroFitInstance.getRetrofit().create(ICreateShare.class);
            Call<ShareHandler> call = shareAPI.createShare(shareHandler);
            call.enqueue(new Callback<ShareHandler>() {
                @Override
                public void onResponse(Call<ShareHandler> call, Response<ShareHandler> response) {
                    dialog.dismiss();
                    Log.d(TAG, "onResponse: Server Response: " + response.toString());
                    Log.d(TAG, "onResponse: Received Information: " + response.body().toString());
                    Toast.makeText(context, "Shared Successfully", Toast.LENGTH_SHORT).show();
                    ((Activity) (context)).finish();

                }

                @Override
                public void onFailure(Call<ShareHandler> call, Throwable t) {
                    Log.e(TAG, "onFailure: Something Went Wrong: " + t.getMessage());
                    Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    ((Activity) (context)).finish();
                }
            });
        }catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }
    }

    public void deleteShare(final ShareHandler shareHandler, final Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Canceling...");
        dialog.show();

        try {
            RetroFitInstance.getRetrofit().create(IDeleteShare.class)
                    .deleteShare(shareHandler)
                    .enqueue(new Callback<ShareHandler>() {
                        @Override
                        public void onResponse(Call<ShareHandler> call, Response<ShareHandler> response) {
                            dialog.dismiss();
                            Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                            Log.d(context.getClass().getSimpleName(), "onResponse: Received Information: " + response.body().toString());
                            Toast.makeText(context, "Collaboration cancelled", Toast.LENGTH_SHORT).show();
                            ((Activity) (context)).finish();
                        }

                        @Override
                        public void onFailure(Call<ShareHandler> call, Throwable t) {
                            Log.e(context.getClass().getSimpleName(), "onFailure: Something Went Wrong: " + t.getMessage());
                            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                            ((Activity) (context)).finish();
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }
    }
}
