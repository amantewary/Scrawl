package com.example.amantewary.scrawl.API;

import android.support.annotation.NonNull;

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.List;

public interface IGetNoteById {

    void onSuccess(@NonNull List<NoteHandler> note);

    void onError(@NonNull Throwable throwable);
}
