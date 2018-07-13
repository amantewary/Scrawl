package com.example.amantewary.scrawl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.INoteAPI;
import com.example.amantewary.scrawl.Adapters.NotesList;
import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestHandler {


    public void createNote(NoteHandler noteHandler, final Context context) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.show();
        INoteAPI noteAPI = RetroFitInstance.getRetrofit().create(INoteAPI.class);
        Call<NoteHandler> call = noteAPI.createNote(noteHandler);
        call.enqueue(new Callback<NoteHandler>() {
            @Override
            public void onResponse(Call<NoteHandler> call, Response<NoteHandler> response) {
                dialog.dismiss();
                Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                Log.d(context.getClass().getSimpleName(), "onResponse: Received Information: " + response.body().toString());
                Toast.makeText(context, "Note Created", Toast.LENGTH_SHORT).show();
                ((Activity)(context)).finish();
            }

            @Override
            public void onFailure(Call<NoteHandler> call, Throwable t) {
                Log.e(context.getClass().getSimpleName(), "onFailure: Something Went Wrong: " + t.getMessage());
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                ((Activity)(context)).finish();
            }
        });
    }

    public void getNoteList(final Context context, final RecyclerView notesListView){
        INoteAPI noteAPI = RetroFitInstance.getRetrofit().create(INoteAPI.class);
        Call<List<NoteHandler>> call = noteAPI.getNotes();

        call.enqueue(new Callback<List<NoteHandler>>() {
            @Override
            public void onResponse(Call<List<NoteHandler>> call, Response<List<NoteHandler>> response) {
                Log.d(context.getClass().getSimpleName(), "onResponse: Server Response: " + response.toString());
                Log.d(context.getClass().getSimpleName(), "onResponse: Received Information: " + response.body().toString());
                List<NoteHandler> notes = response.body();
                NotesList notesAdapter = new NotesList(context, notes);
                notesListView.setAdapter(notesAdapter);
                notesListView.setLayoutManager(new LinearLayoutManager(context));
            }

            @Override
            public void onFailure(Call<List<NoteHandler>> call, Throwable t) {
                Log.e(context.getClass().getSimpleName(), "onFailure: Something Went Wrong: " + t.getMessage());
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
