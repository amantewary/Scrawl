package com.example.amantewary.scrawl.API.Users;

import android.support.annotation.NonNull;

public interface IUserExistResponse {

    void onSuccess(@NonNull Boolean ifExist);

    void onError(@NonNull Throwable throwable);
}
