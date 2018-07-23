package com.example.amantewary.scrawl.API.Shares;

import com.example.amantewary.scrawl.Handlers.ShareHandler;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;

public interface IDeleteShare {
    @HTTP(method = "DELETE", path = "~hhou/QA_Devint/NoteApi/v1/notes/delete", hasBody = true)
    Call<ShareHandler> deleteShare(@Body ShareHandler shareHandler);
}
