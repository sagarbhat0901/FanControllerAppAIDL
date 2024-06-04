package com.example.fancontrollerapp;

import androidx.annotation.NonNull;
import androidx.car.app.CarAppService;
import androidx.car.app.Session;
import androidx.car.app.validation.HostValidator;

/**
 * Main service for the car app which manages sessions and host validation.
 */
public class MainCarAppService extends CarAppService {

    /**
     * Creates a host validator to validate the host connecting to the car app.
     *
     * @return a HostValidator instance that allows all hosts.
     */
    @NonNull
    @Override
    public HostValidator createHostValidator() {
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR;
    }

    /**
     * Creates a new session for the car app.
     *
     * @return a new instance of MainSession.
     */
    @NonNull
    @Override
    public Session onCreateSession() {
        return new MainSession();
    }

}
