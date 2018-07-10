package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.LoginUserClass;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface IRegisterUser {

    @Multipart
    @POST("~kamath/QA_Devint/register.php")
    Call<LoginUserClass> sendPostRegister(@PartMap Map<String, RequestBody> parameters);
}
