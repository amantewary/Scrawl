package com.example.amantewary.scrawl.API;

import android.support.annotation.NonNull;

import com.example.amantewary.scrawl.Handlers.DataHandler;

import java.util.List;

public interface IHandleResponse {

    void onSuccess(@NonNull List<DataHandler> label);

    void onError(@NonNull Throwable throwable);
}
