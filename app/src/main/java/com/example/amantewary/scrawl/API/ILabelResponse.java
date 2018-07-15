package com.example.amantewary.scrawl.API;

import android.support.annotation.NonNull;

import com.example.amantewary.scrawl.Handlers.LabelHandler;

import java.util.List;

public interface ILabelResponse {

    void onSuccess(@NonNull List<LabelHandler> labels);

    void onError(@NonNull Throwable throwable);
}
