package com.example.amantewary.scrawl.API.Notes;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ICreateNote {
    @POST("~kamath/QA_Devint/NoteApi/v1/notes/create")
    Call<NoteHandler> createNote(@Body NoteHandler note);
}
