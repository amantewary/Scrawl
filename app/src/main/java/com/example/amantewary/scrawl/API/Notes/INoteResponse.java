package com.example.amantewary.scrawl.API.Notes;

import android.support.annotation.NonNull;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

public interface INoteResponse {

    void onSuccess(@NonNull List<NoteHandler> note);

    void onError(@NonNull Throwable throwable);
}
