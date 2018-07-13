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


    ArrayList<String> labelHandlers;

    public LabelHandler() {
    }

    public LabelHandler(ArrayList<String> labelHandlers) {
        this.labelHandlers = labelHandlers;
    }

    public LabelHandler(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }


    public ArrayList<String> getLabelHandlers() {
        return labelHandlers;
    }



}
