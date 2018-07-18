package com.example.amantewary.scrawl;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.INoteAPI;
import com.example.amantewary.scrawl.API.INoteResponse;
import com.example.amantewary.scrawl.API.IShareAPI;
import com.example.amantewary.scrawl.Adapters.NotesList;
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

    public Call<List<NoteHandler>> getAllNotesByUserId(final Context context, String share_to, Integer userid, @Nullable final INoteResponse callbacks) {
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
                        callbacks.onError(t);
                    }
                });

        return RetroFitInstance.getRetrofit().create(IShareAPI.class)
                .getAllNotesByUserID(share_to, userid);
    }



//    public Call<List<NoteHandler>> getAllNotesByUserId(final Context context, Integer share_to, @Nullable final INoteResponse callbacks) {
//        dialog = new ProgressDialog(context);
//        dialog.setMessage("Loading...");
//        dialog.show();
//
//        RetroFitInstance.getRetrofit().create(IShareAPI.class)
//                .getSharedNotesByUserID(share_to)
//                .enqueue(new Callback<List<NoteHandler>>() {
//                    @Override
//                    public void onResponse(Call<List<NoteHandler>> call, Response<List<NoteHandler>> response) {
////                        dialog.dismiss();
//                        Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
//
//                        notes = response.body();
//
//                        NotesRequestHandler request = new NotesRequestHandler();
//                        request.getNoteList(SharesRequestHandler.this, new INoteResponse() {
//                            @Override
//                            public int hashCode() {
//                                return super.hashCode();
//                            }
//
//                            @Override
//                            public void onSuccess(@NonNull List<NoteHandler> note) {
//                                Log.d(TAG, "onResponse: Received Information: " + note.toString());
//                                NotesList notesAdapter = new NotesList(SharesRequestHandler.this, note);
//                                FastScrollRecyclerView notesListView.setAdapter(notesAdapter);
//                                notesListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable throwable) {
//                                Log.e(TAG, "onFailure: Something Went Wrong: " + throwable.getMessage());
//                                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//
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
//
//        return RetroFitInstance.getRetrofit().create(INoteAPI.class)
//                .getSingleNote(noteId);
//
//
//    }

}