package com.example.amantewary.scrawl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.Notes.INoteResponse;
import com.example.amantewary.scrawl.API.Notes.ICreateNote;
import com.example.amantewary.scrawl.API.Notes.IDeleteNote;
import com.example.amantewary.scrawl.API.Notes.IGetNote;
import com.example.amantewary.scrawl.API.Notes.IGetNoteById;
import com.example.amantewary.scrawl.API.Notes.IGetNoteByLabel;
import com.example.amantewary.scrawl.API.Notes.IUpdateNote;
import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesRequestHandler {

    private List<NoteHandler> notes;
    private ProgressDialog dialog;
    private SessionManager sessionManager;

    public void createNote(NoteHandler noteHandler, final Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.show();
        RetroFitInstance.getRetrofit().create(ICreateNote.class)
                .createNote(noteHandler)
                .enqueue(new Callback<NoteHandler>() {
                    @Override
                    public void onResponse(Call<NoteHandler> call, Response<NoteHandler> response) {
                        dialog.dismiss();
                        Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                        Log.d(context.getClass().getSimpleName(), "onResponse: Received Information: " + response.body().toString());
                        Toast.makeText(context, "Note Created", Toast.LENGTH_SHORT).show();
                        ((Activity) (context)).finish();
                    }

                    @Override
                    public void onFailure(Call<NoteHandler> call, Throwable t) {
                        Log.e(context.getClass().getSimpleName(), "onFailure: Something Went Wrong: " + t.getMessage());
                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        ((Activity) (context)).finish();
                    }
                });
    }

    public void getNoteList(final Context context, Integer user_id ,@Nullable final INoteResponse callbacks) {
        dialog = new ProgressDialog(context);
        sessionManager = new SessionManager(context);
        dialog.setMessage("Loading...");
        dialog.show();
        RetroFitInstance.getRetrofit().create(INoteAPI.class)
                .getNotesByUser(Integer.valueOf(sessionManager.getUserDetails().get("userId")))
        RetroFitInstance.getRetrofit().create(IGetNote.class)
                .getNotesByUser(user_id)
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
    }

    public void getSingleNote(final Context context, Integer noteId, @Nullable final INoteResponse callbacks) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.show();
        RetroFitInstance.getRetrofit().create(IGetNoteById.class)
                .getSingleNote(noteId)
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
    }

    public void getNotesListByLabel(final Context context, String label_name, @Nullable final INoteResponse callbacks) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.show();
        RetroFitInstance.getRetrofit().create(IGetNoteByLabel.class)
                .getNotesByLabel(label_name)
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

    public void editNote(NoteHandler noteHandler, final Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.show();
        RetroFitInstance.getRetrofit().create(IUpdateNote.class)
                .updateNote(noteHandler)
                .enqueue(new Callback<NoteHandler>() {
                    @Override
                    public void onResponse(Call<NoteHandler> call, Response<NoteHandler> response) {
                        dialog.dismiss();
                        Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                        Log.d(context.getClass().getSimpleName(), "onResponse: Received Information: " + response.body().toString());
                        Toast.makeText(context, "Note Edited", Toast.LENGTH_SHORT).show();
                        ((Activity) (context)).finish();
                    }

                    @Override
                    public void onFailure(Call<NoteHandler> call, Throwable t) {
                        Log.e(context.getClass().getSimpleName(), "onFailure: Something Went Wrong: " + t.getMessage());
                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        ((Activity) (context)).finish();
                    }
                });
    }

    public void deleteNote(final NoteHandler noteHandler, final Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.show();
        RetroFitInstance.getRetrofit().create(IDeleteNote.class)
                .deleteNote(noteHandler)
                .enqueue(new Callback<NoteHandler>() {
                    @Override
                    public void onResponse(Call<NoteHandler> call, Response<NoteHandler> response) {
                        dialog.dismiss();
                        Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                        Log.d(context.getClass().getSimpleName(), "onResponse: Received Information: " + response.body().toString());
                        Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show();
                        ((Activity) (context)).finish();
                    }

                    @Override
                    public void onFailure(Call<NoteHandler> call, Throwable t) {
                        Log.e(context.getClass().getSimpleName(), "onFailure: Something Went Wrong: " + t.getMessage());
                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        ((Activity) (context)).finish();
                    }
                });
    }

}
