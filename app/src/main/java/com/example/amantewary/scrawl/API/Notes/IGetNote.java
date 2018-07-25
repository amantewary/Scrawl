package com.example.amantewary.scrawl.API.Notes;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IGetNote {
    @Headers("Content-Type: application/json")
    @GET("~hhou/QA_Devint/NoteApi/v1/shares/getAllNotesForUser")
    Call<List<NoteHandler>> getAllNotesByUserID(@Query("share_to") String share_to, @Query("userid") Integer userid);
}