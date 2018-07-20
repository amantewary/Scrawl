package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.LoginUserClass;
import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.example.amantewary.scrawl.Handlers.ShareHandler;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface IShareAPI {

    @POST("~hhou/QA_Devint/NoteApi/v1/shares/create")
    Call<ShareHandler> createShare(@Body ShareHandler share);

    @Multipart
    @POST("~hhou/QA_Devint/checkIfUserExist.php")
    Call<LoginUserClass> checkIfUserExists(@PartMap Map<String, RequestBody> parameters);

    @Headers("Content-Type: application/json")
    @GET("~hhou/QA_Devint/NoteApi/v1/shares/readNoteByUserId")
    Call<List<NoteHandler>> getSharedNotesByUserID(@Query("share_to") Integer share_to);

//  TODO: Moved To IGetNote
//    @Headers("Content-Type: application/json")
//    @GET("~hhou/QA_Devint/NoteApi/v1/shares/getAllNotesForUser")
//    Call<List<NoteHandler>> getAllNotesByUserID(@Query("share_to") String share_to, @Query("userid") Integer userid);

}
