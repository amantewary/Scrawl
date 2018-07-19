package com.example.amantewary.scrawl;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.amantewary.scrawl.API.IShareAPI;
import com.example.amantewary.scrawl.API.Notes.INoteResponse;
import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharesRequestHandler {

    private static final String TAG = "SharesRequestHandler";
    private List<NoteHandler> notes;
    private ProgressDialog dialog;

    public void getAllNotesByUserId(final Context context, String share_to, Integer userid, @Nullable final INoteResponse callbacks) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.show();

        RetroFitInstance.getRetrofit().create(IShareAPI.class)
                .getAllNotesByUserID(share_to, userid)
                .enqueue(new Callback<List<NoteHandler>>() {
                    @Override
                    public void onResponse(Call<List<NoteHandler>> call, Response<List<NoteHandler>> response) {
                        dialog.dismiss();

                        Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());

                        notes = response.body();

                        if (callbacks != null) {
                            callbacks.onSuccess(notes);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<NoteHandler>> call, Throwable t) {
                        dialog.dismiss();
                        callbacks.onError(t);
                    }
                });
    }

}