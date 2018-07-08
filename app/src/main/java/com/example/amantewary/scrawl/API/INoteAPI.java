package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface INoteAPI {
    @GET("read")
    Call<List<NoteHandler>> getNotes();

    @POST("create")
    Call<NoteHandler> createNote(@Body NoteHandler note);


}
