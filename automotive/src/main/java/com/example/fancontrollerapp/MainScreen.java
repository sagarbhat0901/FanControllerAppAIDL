package com.example.fancontrollerapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarText;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.car.app.model.Toggle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MainScreen extends Screen {
    private static final String TOGGLE_TITLE_ON = "On";
    private static final String TOGGLE_TITLE_OFF = "Off";
    private final FanSpeedClient fanSpeedClient;
    private boolean fanOn;
    private String toggleTitle;

    public MainScreen(@NonNull CarContext carContext) {
        super(carContext);
        fanSpeedClient = new FanSpeedClient();
        fanSpeedClient.bindService(carContext);
        fanOn = fanSpeedClient.isFanOn();
        if(fanOn) {
            toggleTitle = TOGGLE_TITLE_OFF;
        }
        else {
            toggleTitle = TOGGLE_TITLE_ON;
        }
        registerLifecycleObserver();
    }

    private void registerLifecycleObserver() {
        getLifecycle().addObserver(new LifecycleObserver() {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            public void onDestroy() {
                fanSpeedClient.unbindService(getCarContext());
                Log.i("MyTag", "Screen Destroyed");
            }});
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        int currentSpeed = fanSpeedClient.getFanSpeed();
        String fanStatusAndSpeed = "Fan Status: " + (fanOn ? "ON" : "OFF") + "  |  Current Speed: " + currentSpeed;

        Toggle toggle = new Toggle.Builder(new Toggle.OnCheckedChangeListener() {
            @Override
            public void onCheckedChange(boolean isChecked) {
                if (isChecked) {
                    fanSpeedClient.turnFanOn();
                    fanOn = true;
                    toggleTitle = TOGGLE_TITLE_OFF;
                } else {
                    fanSpeedClient.turnFanOff();
                    fanOn = false;
                    toggleTitle = TOGGLE_TITLE_ON;
                }
                invalidate();
            }
        }).setChecked(fanOn).build();

        Action increaseSpeedAction = new Action.Builder()
                .setTitle("Increase Speed")
                .setOnClickListener(() -> {
                    fanSpeedClient.increaseFanSpeed();
                    invalidate();
                })
                .build();

        Action decreaseSpeedAction = new Action.Builder()
                .setTitle("Decrease Speed")
                .setOnClickListener(() -> {
                    fanSpeedClient.decreaseFanSpeed();
                    invalidate();
                })
                .build();

        Pane pane = new Pane.Builder()
                .addAction(increaseSpeedAction)
                .addAction(decreaseSpeedAction)
                .addRow(new Row.Builder().setTitle("Turn Fan " + toggleTitle).setToggle(toggle).build())
                .build();

        return new PaneTemplate.Builder(pane)
                .setTitle(fanStatusAndSpeed)
                .setHeaderAction(Action.APP_ICON)
                .build();
    }

}
