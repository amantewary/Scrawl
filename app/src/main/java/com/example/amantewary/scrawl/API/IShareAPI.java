package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.example.amantewary.scrawl.Handlers.ShareHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IShareAPI {

    @POST("~hhou/QA_Devint/NoteApi/v1/shares/create")
    Call<ShareHandler> createShare(@Body ShareHandler share);
}
