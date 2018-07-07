package com.example.amantewary.scrawl.API;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface INoteAPI {
    @GET("read")
    Call<List<NoteHandler>> getNotes();

    @FormUrlEncoded
    @POST("create")
    Call<NoteHandler> createNote(
            @Field("label_id") Integer label_id,
            @Field("title") String title,
            @Field("body") String body,
            @Field("url") String url,
            @Field("author_id") Integer author_id
    );


}
