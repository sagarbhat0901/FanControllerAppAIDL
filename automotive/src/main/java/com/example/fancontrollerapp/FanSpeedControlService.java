package com.example.fancontrollerapp;



import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class FanSpeedControlService extends Service {

    private int fanSpeed = 0;
    private boolean isFanOn = false;

    // Implementation of the AIDL Interface
    private final IFanSpeedControlService.Stub binder = new IFanSpeedControlService.Stub() {
        @Override
        public void increaseFanSpeed(){
            if (isFanOn) {
                if (fanSpeed < 50 && fanSpeed >= 0) {
                    fanSpeed += 10;
                } else if (fanSpeed >= 50) {
                    Toast.makeText(getApplicationContext(), "Fan speed is already at maximum", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void decreaseFanSpeed(){
            if (isFanOn) {
                if(fanSpeed > 0 && fanSpeed <= 50) {
                    fanSpeed -= 10;
                } else if (fanSpeed <= 0) {
                    Toast.makeText(getApplicationContext(), "Fan speed is already at minimum", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void turnFanOn() {
            if (isFanOn) {
                Toast.makeText(getApplicationContext(), "Fan is already on", Toast.LENGTH_SHORT).show();
            }
            else {
                isFanOn = true;
                Toast.makeText(getApplicationContext(), "Fan turned on", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void turnFanOff() {
            if (!isFanOn) {
                Toast.makeText(getApplicationContext(), "Fan is already off", Toast.LENGTH_SHORT).show();
            } else {
                isFanOn = false;
                Toast.makeText(getApplicationContext(), "Fan turned off", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public int getFanSpeed(){
            return isFanOn ? fanSpeed : 0;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
