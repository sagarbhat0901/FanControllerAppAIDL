package com.example.fancontrollerapp;



import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class FanSpeedControlService extends Service {

    // Implementation of the AIDL Interface
    private final IFanSpeedControlService.Stub binder = new IFanSpeedControlService.Stub() {
        @Override
        public void increaseFanSpeed(){
            if (!NativeUtils.isFanOnHal()) {
                Toast.makeText(getApplicationContext(), "Turn On the Fan first", Toast.LENGTH_SHORT).show();
            } else if (!NativeUtils.increaseFanSpeedHal()) {
                Toast.makeText(getApplicationContext(), "Fan speed is already at maximum", Toast.LENGTH_SHORT).show();
            }
            else if (!isFanOn) {
                Toast.makeText(getApplicationContext(), "Turn On the Fan first", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void decreaseFanSpeed(){
            if (!NativeUtils.isFanOnHal()) {
                Toast.makeText(getApplicationContext(), "Turn On the Fan first", Toast.LENGTH_SHORT).show();
            } else if (!NativeUtils.decreaseFanSpeedHal()) {
                Toast.makeText(getApplicationContext(), "Fan speed is already at minimum", Toast.LENGTH_SHORT).show();
            }
            else if (!isFanOn) {
                Toast.makeText(getApplicationContext(), "Turn On the Fan first", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void turnFanOn() {
            if (!NativeUtils.turnFanOnHal()) {
                Toast.makeText(getApplicationContext(), "Fan is already on", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Fan turned on", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void turnFanOff() {
            if (!NativeUtils.turnFanOffHal()) {
                Toast.makeText(getApplicationContext(), "Fan is already off", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fan turned off", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public int getFanSpeed(){
            return NativeUtils.getFanSpeedHal();
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
