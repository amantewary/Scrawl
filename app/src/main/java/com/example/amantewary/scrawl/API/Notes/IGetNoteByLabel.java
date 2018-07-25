package com.example.amantewary.scrawl.API.Notes;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IGetNoteByLabel {
    @Headers("Content-Type: application/json")
    @GET("~kamath/QA_Devint/NoteApi/v1/notes/readByLabel")
    Call<List<NoteHandler>> getNotesByLabel(@Query("label_name") String label_name, @Query("user_id") Integer user_id);

}
