package com.example.fancontrollerapp;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.Session;

public class MainSession extends Session {

    private FanSpeedClient fanSpeedClient;

    public MainSession() {
        super();
        fanSpeedClient = new FanSpeedClient();
      //  fanSpeedClient.bindService(getCarContext());
    }


    @NonNull
    @Override
    public Screen onCreateScreen(@NonNull Intent intent) {
        return new MainScreen(getCarContext());
    }
}
