package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.LabelHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Structural Pattern: Facade Pattern
public interface ILabelAPI {
    @GET("~kamath/QA_Devint/NoteApi/v1/label/read")
    Call<List<LabelHandler>> getLabels();
}
