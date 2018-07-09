package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.LoginUserClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface LoginUser {
    @POST("/login.php")
    Call<List<LoginUserClass>> getAllUser();
}
