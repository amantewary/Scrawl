package com.example.amantewary.scrawl.Handlers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class ShareHandler {

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("share_from")
    @Expose
    String share_from;

    @SerializedName("share_to")
    @Expose
    String share_to;

    @SerializedName("note_id")
    @Expose
    Integer note_id;

    @SerializedName("shared_at")
    @Expose
    Date shared_at;

    public ShareHandler(String share_from, String share_to, Integer note_id) {
        this.share_from = share_from;
        this.share_to = share_to;
        this.note_id = note_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShare_from() {
        return share_from;
    }

    public void setShare_from(String share_from) {
        this.share_from = share_from;
    }

    public String getShare_to() {
        return share_to;
    }

    public void setShare_to(String share_to) {
        this.share_to = share_to;
    }

    public Integer getNote_id() {
        return note_id;
    }

    public void setNote_id(Integer note_id) {
        this.note_id = note_id;
    }

    public Date getShared_at() {
        return shared_at;
    }

    public void setShared_at(Date shared_at) {
        this.shared_at = shared_at;
    }
}
