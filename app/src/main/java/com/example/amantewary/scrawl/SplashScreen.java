package com.example.amantewary.scrawl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        sessionManager = new SessionManager(getApplicationContext());
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }

    private class LogoLauncher extends Thread {
        public void run() {
            try {
                sleep(50);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (sessionManager.checkLogin()) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(), ActivityRegister.class));
                finish();

            }

        }

    }


}
