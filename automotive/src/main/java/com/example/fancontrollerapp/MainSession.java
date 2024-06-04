package com.example.fancontrollerapp;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.car.app.Screen;
import androidx.car.app.Session;

/**
 * Main session for the car app which manages the creation of screens and initialization of services.
 */
public class MainSession extends Session {

    private FanSpeedClient fanSpeedClient;

    /**
     * Constructor for MainSession.
     * Initializes the FanSpeedClient and binds the service.
     */
    public MainSession() {
        super();
        fanSpeedClient = new FanSpeedClient();
    }

    /**
     * Creates the main screen for the car app session.
     * This method is called when a new screen is needed.
     *
     * @param intent The intent used to create the screen.
     * @return The main screen of the car app.
     */
    @NonNull
    @Override
    public Screen onCreateScreen(@NonNull Intent intent) {
        return new MainScreen(getCarContext());
    }
}
