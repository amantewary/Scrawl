package com.example.amantewary.scrawl;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;


import com.example.amantewary.scrawl.API.IShareAPI;
import com.example.amantewary.scrawl.API.Notes.IGetNote;
import com.example.amantewary.scrawl.API.Notes.INoteResponse;
import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.l4digital.fastscroll.FastScrollRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharesRequestHandler {

    private static final String TAG = "SharesRequestHandler";
    private List<NoteHandler> notes;
    private ProgressDialog dialog;

//    public Call<List<NoteHandler>> getAllNotesByUserId(final Context context, String share_to, Integer userid, @Nullable final INoteResponse callbacks) {
//        dialog = new ProgressDialog(context);
//        dialog.setMessage("Loading...");
//        dialog.show();
//
//        RetroFitInstance.getRetrofit().create(IShareAPI.class)
//                .getAllNotesByUserID(share_to, userid)
//                .enqueue(new Callback<List<NoteHandler>>() {
//                    @Override
//                    public void onResponse(Call<List<NoteHandler>> call, Response<List<NoteHandler>> response) {
//                        dialog.dismiss();
//
//                        Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
//
//                        notes = response.body();
//
//                        if (callbacks != null) {
//                            callbacks.onSuccess(notes);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<NoteHandler>> call, Throwable t) {
//                        dialog.dismiss();
//                        callbacks.onError(t);
//                    }
//                });
//
//        return RetroFitInstance.getRetrofit().create(IShareAPI.class)
//                .getAllNotesByUserID(share_to, userid);
//    }

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

//    public void getNoteList(final Context context, Integer user_id ,@Nullable final INoteResponse callbacks) {
//        dialog = new ProgressDialog(context);
//        dialog.setMessage("Loading...");
//        dialog.show();
//        RetroFitInstance.getRetrofit().create(IGetNote.class)
//                .getNotesByUser(user_id)
//                .enqueue(new Callback<List<NoteHandler>>() {
//                    @Override
//                    public void onResponse(Call<List<NoteHandler>> call, Response<List<NoteHandler>> response) {
//                        dialog.dismiss();
//                        Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
//                        notes = response.body();
//                        if (callbacks != null) {
//                            callbacks.onSuccess(notes);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<NoteHandler>> call, Throwable t) {
//                        callbacks.onError(t);
//                    }
//                });
//    }

}