package com.example.amantewary.scrawl.API.Notes;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IGetNoteById {
    @Headers("Content-Type: application/json")
    @GET("~kamath/QA_Devint/NoteApi/v1/notes/read_single")
    Call<List<NoteHandler>> getSingleNote(@Query("id") Integer id);
}
