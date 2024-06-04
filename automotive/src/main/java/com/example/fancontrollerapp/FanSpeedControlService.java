package com.example.fancontrollerapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

/**
 * Service to control the fan speed through the AIDL interface.
 * Provides methods to increase/decrease fan speed, turn the fan on/off, and get the current fan speed.
 */
public class FanSpeedControlService extends Service {

    // Implementation of the AIDL Interface
    private final IFanSpeedControlService.Stub binder = new IFanSpeedControlService.Stub() {
        /**
         * Increases the fan speed if the fan is currently on.
         * Displays a Toast message if the fan is off or if the speed is already at maximum.
         */
        @Override
        public void increaseFanSpeed() {
            if (!NativeUtils.isFanOnHal()) {
                Toast.makeText(getApplicationContext(), "Turn On the Fan first", Toast.LENGTH_SHORT).show();
            } else if (!NativeUtils.increaseFanSpeedHal()) {
                Toast.makeText(getApplicationContext(), "Fan speed is already at maximum", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * Decreases the fan speed if the fan is currently on.
         * Displays a Toast message if the fan is off or if the speed is already at minimum.
         */
        @Override
        public void decreaseFanSpeed() {
            if (!NativeUtils.isFanOnHal()) {
                Toast.makeText(getApplicationContext(), "Turn On the Fan first", Toast.LENGTH_SHORT).show();
            } else if (!NativeUtils.decreaseFanSpeedHal()) {
                Toast.makeText(getApplicationContext(), "Fan speed is already at minimum", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * Turns the fan on if it is currently off.
         * Displays a Toast message indicating whether the fan was turned on or if it was already on.
         */
        @Override
        public void turnFanOn() {
            if (!NativeUtils.turnFanOnHal()) {
                Toast.makeText(getApplicationContext(), "Fan is already on", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fan turned on", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * Turns the fan off if it is currently on.
         * Displays a Toast message indicating whether the fan was turned off or if it was already off.
         */
        @Override
        public void turnFanOff() {
            if (!NativeUtils.turnFanOffHal()) {
                Toast.makeText(getApplicationContext(), "Fan is already off", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fan turned off", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * Retrieves the current fan speed.
         *
         * @return the current fan speed if the fan is on, otherwise 0.
         */
        @Override
        public int getFanSpeed() {
            return NativeUtils.getFanSpeedHal();
        }

        /**
         * Retrieves the fan on/off status.
         *
         * @return true if fan is on otherwise false.
         */
        @Override
        public boolean isFanOn() throws RemoteException {
            return NativeUtils.isFanOnHal();
        }
    };


    /**
     * Called when a client binds to the service.
     *
     * @param intent The Intent that was used to bind to this service.
     * @return the binder to the client.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
