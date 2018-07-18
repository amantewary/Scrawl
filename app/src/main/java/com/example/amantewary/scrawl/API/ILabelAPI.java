package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.LabelHandler;
import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

//Structural Pattern: Facade Pattern
public interface ILabelAPI {
    @Headers("Content-Type: application/json")
    @GET("~kamath/QA_Devint/NoteApi/v1/label/read")
    Call<List<LabelHandler>> getLabels(@Query("user_id") Integer user_id);

    @POST("~kamath/QA_Devint/NoteApi/v1/label/create")
    Call<NoteHandler> createLabel(@Body LabelHandler label);
}
