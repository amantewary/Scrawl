package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.LoginUserClass;
import com.example.amantewary.scrawl.Handlers.ShareHandler;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface IShareAPI {

    @POST("~hhou/QA_Devint/NoteApi/v1/shares/create")
    Call<ShareHandler> createShare(@Body ShareHandler share);

    @Multipart
    @POST("~hhou/QA_Devint/checkIfUserExist.php")
    Call<LoginUserClass> checkIfUserExists(@PartMap Map<String, RequestBody> parameters);


}
