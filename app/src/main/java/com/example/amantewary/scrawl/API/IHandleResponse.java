package com.example.amantewary.scrawl.API;

import android.support.annotation.NonNull;

import java.util.List;

public interface IHandleResponse <T> {

    void onSuccess(@NonNull List<T> label);

    void onError(@NonNull Throwable throwable);
}
