package com.example.fancontrollerapp;

import android.content.Context;
import android.content.Intent;

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
import androidx.lifecycle.LifecycleObserver;

public class MainScreen extends Screen {
    private Context context;
    private FanSpeedControlService fanSpeedControlService;
    private boolean isBound = false;
    private FanSpeedClient fanSpeedClient;
    private boolean fanOn;

    public MainScreen(@NonNull CarContext carContext) {
        super(carContext);
       fanSpeedClient = new FanSpeedClient();
        fanSpeedClient.bindService(carContext);
    }

    /*init {
        lifecycle.addObserver(object :LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStart() {
                Log.i("mytag", "HelloWorldScreen onStart() method")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStop() {
                Log.i("mytag", "HelloWorldScreen onStop() method")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                Log.i("mytag", "HelloWorldScreen onDestroy() method")
            }
        })
    }*/

    @NonNull
    @Override
    public Template onGetTemplate() {
        int currentSpeed = fanSpeedClient.getFanSpeed();
        String speedText = "Fan Speed: " + currentSpeed;

        Toggle toggle = new Toggle.Builder(new Toggle.OnCheckedChangeListener() {
            @Override
            public void onCheckedChange(boolean isChecked) {
                if (isChecked) {
                    fanSpeedClient.turnFanOn();
                } else {
                    fanSpeedClient.turnFanOff();
                }
            }
        }).setChecked(false).build();






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
                .addRow(new Row.Builder().setTitle("Fan On/Off").setToggle(toggle).build())
                .build();

        return new PaneTemplate.Builder(pane)
                .setTitle(speedText)
                .setHeaderAction(Action.APP_ICON)
                .build();
    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        fanSpeedClient.unbindService(getCarContext());
    }*/
}
