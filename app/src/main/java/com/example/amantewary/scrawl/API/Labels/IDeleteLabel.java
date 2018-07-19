package com.example.amantewary.scrawl.API.Labels;

import com.example.amantewary.scrawl.Handlers.LabelHandler;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;

public interface IDeleteLabel {
    @HTTP(method = "DELETE", path = "~kamath/QA_Devint/NoteApi/v1/label/delete", hasBody = true)
    Call<LabelHandler> deleteLabel(@Body LabelHandler label);
}
