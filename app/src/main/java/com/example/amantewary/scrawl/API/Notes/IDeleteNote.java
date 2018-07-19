package com.example.amantewary.scrawl.API.Notes;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;

public interface IDeleteNote {
    @HTTP(method = "DELETE", path = "~kamath/QA_Devint/NoteApi/v1/notes/delete", hasBody = true)
    Call<NoteHandler> deleteNote(@Body NoteHandler note);
}
