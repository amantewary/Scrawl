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
    @SerializedName("user_id")
    @Expose
    Integer user_id;
    @SerializedName("old_name")
    @Expose
    String old_name;
    @SerializedName("new_name")
    @Expose
    String new_name;


    public LabelHandler(String name, Integer user_id) {
        this.name = name;
        this.user_id = user_id;
    }



    public LabelHandler(String new_name, Integer user_id, String old_name) {
        this.user_id = user_id;
        this.old_name = old_name;
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


}
