package com.example.fancontrollerapp;



/*
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.car.app.Screen;
import androidx.car.app.Session;

public class FanControllerSession extends Session {
    @NonNull
    @Override
    public Screen onCreateScreen(@NonNull Intent intent) {
        return new FanControllerScreen(getCarContext());
    }
}*/


/*
import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Template;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.OnClickDelegate;

public class FanControllerScreen extends Screen {

    private FanSpeedClient fanSpeedClient;
    private boolean isFanOn;
    private int fanSpeed;

    protected FanControllerScreen(@NonNull CarContext carContext) {
        super(carContext);
        fanSpeedClient = new FanSpeedClient();
        fanSpeedClient.bindService(carContext);
        fanSpeed = fanSpeedClient.getFanSpeed();
        isFanOn = fanSpeedClient.isFanOn();
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        Pane.Builder paneBuilder = new Pane.Builder();

        paneBuilder.addAction(new Action.Builder()
                .setTitle("Increase Speed")
                .setOnClickListener(() -> {
                    fanSpeedClient.increaseFanSpeed();
                    fanSpeed = fanSpeedClient.getFanSpeed();
                    invalidate();
                })
                .build());

        paneBuilder.addAction(new Action.Builder()
                .setTitle("Decrease Speed")
                .setOnClickListener(() -> {
                    fanSpeedClient.decreaseFanSpeed();
                    fanSpeed = fanSpeedClient.getFanSpeed();
                    invalidate();
                })
                .build());

        paneBuilder.addAction(new Action.Builder()
                .setTitle(isFanOn ? "Turn Off" : "Turn On")
                .setOnClickListener(() -> {
                    if (isFanOn) {
                        fanSpeedClient.turnFanOff();
                    } else {
                        fanSpeedClient.turnFanOn();
                    }
                    isFanOn = !isFanOn;
                    fanSpeed = fanSpeedClient.getFanSpeed();
                    invalidate();
                })
                .build());

        paneBuilder.setTitle("Fan Speed: " + fanSpeed);

        return new PaneTemplate.Builder(paneBuilder.build())
                .setActionStrip(new ActionStrip.Builder().addAction(new Action.Builder()
                        .setTitle("Refresh")
                        .setOnClickListener(this::invalidate)
                        .build()).build())
                .build();
    }

    @Override
    public void onDestroy() {
        super.notify();
        fanSpeedClient.unbindService(getCarContext());
    }
}*/





import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.car.app.model.Toggle;

public class FanControllerScreen extends Screen {

    private FanSpeedClient fanSpeedClient;
    private boolean isFanOn;
    private int fanSpeed;

    protected FanControllerScreen(@NonNull CarContext carContext) {
        super(carContext);
       // setThemeResId(R.style.Theme_FanControllerApp);
        fanSpeedClient = new FanSpeedClient();
        fanSpeedClient.bindService(carContext);
        fanSpeed = fanSpeedClient.getFanSpeed();
        isFanOn = fanSpeedClient.isFanOn();
    }

   /*// @Override
    public int getThemeResId() {
        return R.style.Theme_FanControllerApp;
    }*/

    @NonNull
    @Override
    public Template onGetTemplate() {
        Pane.Builder paneBuilder = new Pane.Builder();

        paneBuilder.addRow(new Row.Builder()
                .setTitle("Fan Speed: " + fanSpeed)
                .build());

        paneBuilder.addRow(new Row.Builder()
                .setTitle("Increase Speed")
                .setOnClickListener(() -> {
                    fanSpeedClient.increaseFanSpeed();
                    fanSpeed = fanSpeedClient.getFanSpeed();
                    invalidate();
                })
                .build());

        paneBuilder.addRow(new Row.Builder()
                .setTitle("Decrease Speed")
                .setOnClickListener(() -> {
                    fanSpeedClient.decreaseFanSpeed();
                    fanSpeed = fanSpeedClient.getFanSpeed();
                    invalidate();
                })
                .build());

        paneBuilder.addRow(new Row.Builder()
                .setTitle("Fan On/Off")
                .setToggle(new Toggle.Builder(new Toggle.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChange(boolean isChecked) {
                        if (isChecked) {
                            fanSpeedClient.turnFanOn();
                        } else {
                            fanSpeedClient.turnFanOff();
                        }
                        isFanOn = isChecked;
                        fanSpeed = fanSpeedClient.getFanSpeed();
                        invalidate();
                    }

                })
                        .setChecked(isFanOn) // Set the initial state
                        .build())
                .build());

        return new PaneTemplate.Builder(paneBuilder.build())
                .setActionStrip(new ActionStrip.Builder().addAction(new Action.Builder()
                        .setTitle("Refresh")
                        .setOnClickListener(this::invalidate)
                        .build()).build())
                .build();
    }
}
    /*@Override
    public void onDestroy() {
        super.notify();
        fanSpeedClient.unbindService(getCarContext());
    }*/
   /* @Override
    public void onRemoved() {
        super.onRemoved();
        fanSpeedClient.unbindService(getCarContext());
    }*/


