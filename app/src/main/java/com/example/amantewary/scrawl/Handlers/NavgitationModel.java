package com.example.amantewary.scrawl.Handlers;

import android.graphics.drawable.Drawable;

public class NavgitationModel {

    Drawable labelImageView;
    String title;

    public NavgitationModel(Drawable labelImageView, String title) {
        this.labelImageView = labelImageView;
        this.title = title;
    }

    public Drawable getLabelImageView() {
        return labelImageView;
    }

    public void setLabelImageView(Drawable labelImageView) {
        this.labelImageView = labelImageView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
