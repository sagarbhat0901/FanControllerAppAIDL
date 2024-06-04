package com.example.fancontrollerapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.car.app.model.Toggle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Main screen of the car app that displays and controls the fan speed and status.
 */
public class MainScreen extends Screen {
    private static final String TOGGLE_TITLE_ON = "On";
    private static final String TOGGLE_TITLE_OFF = "Off";
    private final FanSpeedClient fanSpeedClient;
    private boolean fanOn;
    private String toggleTitle;

    /**
     * Constructor for MainScreen.
     * Initializes the FanSpeedClient, binds the service, and sets the initial fan status and toggle title.
     *
     * @param carContext The context of the car app.
     */
    public MainScreen(@NonNull CarContext carContext) {
        super(carContext);
        fanSpeedClient = new FanSpeedClient();
        fanSpeedClient.bindService(carContext);
        fanOn = fanSpeedClient.isFanOn(); // Initialize the fan status from the HAL
        toggleTitle = fanOn ? TOGGLE_TITLE_OFF : TOGGLE_TITLE_ON; // Set initial toggle title based on fan status
        registerLifecycleObserver();
    }

    /**
     * Registers a lifecycle observer to handle the destruction of the screen.
     */
    private void registerLifecycleObserver() {
        getLifecycle().addObserver(new LifecycleObserver() {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            public void onDestroy() {
                fanSpeedClient.unbindService(getCarContext());
                Log.i("MyTag", "Screen Destroyed");
            }});
    }

    /**
     * Creates the template for the main screen of the car app.
     *
     * @return The template containing the fan controls and status display.
     */
    @NonNull
    @Override
    public Template onGetTemplate() {
        int currentSpeed = fanSpeedClient.getFanSpeed(); // Get the current fan speed from the HAL
        String fanStatusAndSpeed = "Fan Status: " + (fanOn ? "ON" : "OFF") + "  |  Current Speed: " + currentSpeed;

        // Create a toggle for turning the fan on and off
        Toggle toggle = new Toggle.Builder(new Toggle.OnCheckedChangeListener() {
            @Override
            public void onCheckedChange(boolean isChecked) {
                if (isChecked) {
                    fanSpeedClient.turnFanOn(); // Turn fan on via HAL
                    fanOn = true;
                    toggleTitle = TOGGLE_TITLE_OFF;
                } else {
                    fanSpeedClient.turnFanOff(); // Turn fan off via HAL
                    fanOn = false;
                    toggleTitle = TOGGLE_TITLE_ON;
                }
                invalidate();
            }
        }).setChecked(fanOn).build();

        // Action to increase the fan speed
        Action increaseSpeedAction = new Action.Builder()
                .setTitle("Increase Speed")
                .setOnClickListener(() -> {
                    fanSpeedClient.increaseFanSpeed(); // Increase fan speed via HAL
                    invalidate();
                })
                .build();

        // Action to decrease the fan speed
        Action decreaseSpeedAction = new Action.Builder()
                .setTitle("Decrease Speed")
                .setOnClickListener(() -> {
                    fanSpeedClient.decreaseFanSpeed(); // Decrease fan speed via HAL
                    invalidate();
                })
                .build();

        // Create a pane to hold the UI elements
        Pane pane = new Pane.Builder()
                .addAction(increaseSpeedAction)
                .addAction(decreaseSpeedAction)
                .addRow(new Row.Builder().setTitle("Turn Fan " + toggleTitle).setToggle(toggle).build())
                .build();

        // Create and return the template
        return new PaneTemplate.Builder(pane)
                .setTitle(fanStatusAndSpeed)
                .setHeaderAction(Action.APP_ICON)
                .build();
    }

}
