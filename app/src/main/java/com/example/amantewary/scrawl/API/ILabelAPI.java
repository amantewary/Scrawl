package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.LabelHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ILabelAPI {
    @GET("read")
    Call<List<LabelHandler>> getLabels();
}
