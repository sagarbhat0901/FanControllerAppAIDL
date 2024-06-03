/*
package com.example.fancontrollerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FanSpeedClient fanSpeedClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fanSpeedClient = new FanSpeedClient();
        fanSpeedClient.bindService(this);

        // Example usage
        findViewById(R.id.buttonIncreaseSpeed).setOnClickListener(v -> {
            fanSpeedClient.increaseFanSpeed();
            updateFanSpeed();
        });

        findViewById(R.id.buttonDecreaseSpeed).setOnClickListener(v -> {
            fanSpeedClient.decreaseFanSpeed();
            updateFanSpeed();
        });

        findViewById(R.id.buttonTurnOn).setOnClickListener(v -> {
            fanSpeedClient.turnFanOn();
            updateFanSpeed();
            //Toast.makeText(this, "Fan turned on", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.buttonTurnOff).setOnClickListener(v -> {
            fanSpeedClient.turnFanOff();
            updateFanSpeed();
            //Toast.makeText(this, "Fan turned off", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateFanSpeed() {
        try {
            int speed = fanSpeedClient.getFanSpeed();
            TextView textView = findViewById(R.id.textView);
            textView.setText(String.valueOf(speed));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fanSpeedClient.unbindService(this);
    }
}
*/
