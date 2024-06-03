package com.example.fancontrollerapp;

import androidx.car.app.CarAppService;
import androidx.car.app.Session;
import androidx.annotation.NonNull;
import androidx.car.app.validation.HostValidator;

public class FanControllerCarAppService extends CarAppService {
    @NonNull
    @Override
    public HostValidator createHostValidator() {
        return null;
    }

    @NonNull
    @Override
    public Session onCreateSession() {
        return new FanControllerSession();
    }
}