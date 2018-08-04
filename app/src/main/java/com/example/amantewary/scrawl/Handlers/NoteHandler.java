package com.example.amantewary.scrawl.Handlers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoteHandler {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("label_name")
    @Expose
    private String label_name;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("user_id")
    @Expose
    private Integer user_id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date")
    @Expose
    private String date;

    public NoteHandler(Integer id) {
        this.id = id;
    }
    public NoteHandler(String label_name, String title, String body, String url, Integer user_id, String status, String date) {
        this.label_name = label_name;
        this.title = title;
        this.body = body;
        this.url = url;
        this.user_id = user_id;
        this.status = status;
        this.date = date;
    }
    public NoteHandler(Integer id, String label_name, String title, String body, String url, Integer user_id, String status, String date) {
        this.id = id;
        this.label_name = label_name;
        this.title = title;
        this.body = body;
        this.url = url;
        this.user_id = user_id;
        this.status = status;
        this.date = date;
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

    public Integer getUser_id() {
        return user_id;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
