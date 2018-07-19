package com.example.amantewary.scrawl.API.Labels;

import com.example.amantewary.scrawl.Handlers.LabelHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IGetLabels {
    @Headers("Content-Type: application/json")
    @GET("~kamath/QA_Devint/NoteApi/v1/label/read")
    Call<List<LabelHandler>> getLabels(@Query("user_id") Integer user_id);
}
