package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.example.amantewary.scrawl.Handlers.ShareHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IShareAPI {

    @POST("create")
    Call<ShareHandler> createShare(@Body ShareHandler share);
}
