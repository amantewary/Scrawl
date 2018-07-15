package com.example.amantewary.scrawl.Handlers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoteHandler {

    @SerializedName("id")
    @Expose
    Integer id;
    @SerializedName("label_name")
    @Expose
    String label_name;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("body")
    @Expose
    String body;
    @SerializedName("url")
    @Expose
    String url;
    @SerializedName("user_id")
    @Expose
    Integer user_id;


    public NoteHandler(Integer id) {
        this.id = id;
    }

    public NoteHandler(String label_name, String title, String body, String url, Integer user_id) {
        this.label_name = label_name;
        this.title = title;
        this.body = body;
        this.url = url;
        this.user_id = user_id;
    }

    public NoteHandler(Integer id, String label_name, String title, String body, String url, Integer user_id) {
        this.id = id;
        this.label_name = label_name;
        this.title = title;
        this.body = body;
        this.url = url;
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
