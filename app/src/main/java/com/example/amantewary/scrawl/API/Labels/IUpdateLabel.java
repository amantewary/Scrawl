package com.example.amantewary.scrawl.API.Labels;

import com.example.amantewary.scrawl.Handlers.LabelHandler;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface IUpdateLabel {
    @PUT("~kamath/QA_Devint/NoteApi/v1/label/update")
    Call<LabelHandler> updateLabel(@Body LabelHandler note);
}
