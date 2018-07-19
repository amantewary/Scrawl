package com.example.amantewary.scrawl.API.Labels;

import com.example.amantewary.scrawl.Handlers.LabelHandler;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ICreateLabel {
    @POST("~kamath/QA_Devint/NoteApi/v1/label/create")
    Call<LabelHandler> createLabel(@Body LabelHandler label);
}
