package com.example.amantewary.scrawl.API.Notes;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface IUpdateNote {
    @PUT("~kamath/QA_Devint/NoteApi/v1/notes/update")
    Call<NoteHandler> updateNote(@Body NoteHandler note);
}
