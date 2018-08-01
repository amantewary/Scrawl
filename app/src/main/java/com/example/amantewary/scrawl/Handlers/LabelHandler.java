package com.example.amantewary.scrawl.Handlers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LabelHandler {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("id")
    @Expose
    Integer id;
    @SerializedName("user_id")
    @Expose
    Integer user_id;
    @SerializedName("old_name")
    @Expose
    String old_name;
    @SerializedName("new_name")
    @Expose
    String new_name;


    ArrayList<String> labelHandlers;

    public LabelHandler() {
    }

    public LabelHandler(ArrayList<String> labelHandlers) {
        this.labelHandlers = labelHandlers;
    }

    public LabelHandler(String name) {
        this.name = name;
    }

    public LabelHandler(String name, Integer user_id) {
        this.name = name;
        this.user_id = user_id;
    }

    public LabelHandler(String new_name, Integer user_id, String old_name) {
        this.user_id = user_id;
        this.old_name = old_name;
        this.new_name = new_name;
    }

    public String getOld_name() {
        return old_name;
    }

    public void setOld_name(String old_name) {
        this.old_name = old_name;
    }

    public String getNew_name() {
        return new_name;
    }

    public void setNew_name(String new_name) {
        this.new_name = new_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public ArrayList<String> getLabelHandlers() {
        return labelHandlers;
    }


}
