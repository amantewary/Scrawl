package com.example.amantewary.scrawl.API.Shares;

import com.example.amantewary.scrawl.Handlers.ShareHandler;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ICreateShare {
    @POST("~hhou/QA_Devint/NoteApi/v1/shares/create")
    Call<ShareHandler> createShare(@Body ShareHandler share);
}
