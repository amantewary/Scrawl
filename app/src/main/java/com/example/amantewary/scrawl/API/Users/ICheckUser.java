package com.example.amantewary.scrawl.API.Users;

import com.example.amantewary.scrawl.Handlers.UserClass;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface ICheckUser {

    @Multipart
    @POST("~hhou/QA_Devint/checkIfUserExist.php")
    Call<UserClass> checkIfUserExists(@PartMap Map<String, RequestBody> parameters);

}
