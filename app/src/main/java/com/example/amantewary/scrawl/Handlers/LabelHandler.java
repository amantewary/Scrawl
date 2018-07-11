package com.example.amantewary.scrawl.Handlers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabelHandler {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("id")
    @Expose
    Integer id;

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
}
