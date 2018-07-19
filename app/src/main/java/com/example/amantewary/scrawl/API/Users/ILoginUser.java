package com.example.amantewary.scrawl.API.Users;

import com.example.amantewary.scrawl.Handlers.LoginUserClass;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface ILoginUser {
    @Multipart
    @POST("~kamath/QA_Devint/login.php")
    Call<LoginUserClass> sendPost(@PartMap Map<String, RequestBody> parameters);
}
