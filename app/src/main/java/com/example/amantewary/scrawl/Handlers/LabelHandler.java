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
