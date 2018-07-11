package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface INoteAPI {
    @GET("~kamath/QA_Devint/NoteApi/v1/notes/read")
    Call<List<NoteHandler>> getNotes();

    @Headers("Content-Type: application/json")
    @GET("~kamath/QA_Devint/NoteApi/v1/notes/read_single")
    Call<List<NoteHandler>> getSingleNote(@Query("id") Integer id);

    @POST("~kamath/QA_Devint/NoteApi/v1/notes/create")
    Call<NoteHandler> createNote(@Body NoteHandler note);


}
