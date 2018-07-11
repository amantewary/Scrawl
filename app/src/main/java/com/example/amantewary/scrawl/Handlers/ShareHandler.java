package com.example.amantewary.scrawl.Handlers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShareHandler {

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("share_from")
    @Expose
    Integer share_from;

    @SerializedName("share_to")
    @Expose
    Integer share_to;

    @SerializedName("note_id")
    @Expose
    Integer note_id;

    public ShareHandler(Integer share_from, Integer share_to, Integer note_id) {
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

    public Integer getShare_from() {
        return share_from;
    }

    public void setShare_from(Integer share_from) {
        this.share_from = share_from;
    }

    public Integer getShare_to() {
        return share_to;
    }

    public void setShare_to(Integer share_to) {
        this.share_to = share_to;
    }

    public Integer getNote_id() {
        return note_id;
    }

    public void setNote_id(Integer note_id) {
        this.note_id = note_id;
    }

}
