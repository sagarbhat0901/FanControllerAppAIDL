package com.example.fancontrollerapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Client class for interacting with the FanSpeedControlService.
 * Provides methods to bind/unbind the service and to control the fan speed.
 */
public class FanSpeedClient {

    private IFanSpeedControlService fanSpeedControlService;
    private boolean isServiceConnected = false;
    Context context;


    private ServiceConnection serviceConnection = new ServiceConnection() {
        /**
         * Called when the service is connected.
         *
         * @param name    The name of the component.
         * @param service The IBinder of the service.
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            fanSpeedControlService = IFanSpeedControlService.Stub.asInterface(service);
            isServiceConnected = true;
        }

        /**
         * Called when the service is disconnected.
         *
         * @param name The name of the component.
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            fanSpeedControlService = null;
            isServiceConnected = false;
        }
    };

    /**
     * Binds to the FanSpeedControlService.
     *
     * @param context The context to use for binding the service.
     */
    public void bindService(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), FanSpeedControlService.class);
        intent.setPackage("com.example.fancontrollerapp.FanSpeedService");
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * Unbinds from the FanSpeedControlService.
     *
     * @param context The context to use for unbinding the service.
     */
    public void unbindService(Context context) {
        if (isServiceConnected) {
            context.unbindService(serviceConnection);
            isServiceConnected = false;
        }
    }

    /**
     * Increases the fan speed by 1.
     * Handles RemoteException if there is an issue with the remote service.
     */
    public void increaseFanSpeed() {
        try {
            if (fanSpeedControlService != null) {
                fanSpeedControlService.increaseFanSpeed();
            }
        } catch (RemoteException e) {
            Log.e("FanSpeedClient", "RemoteException", e);
        }
    }

    /**
     * Decreases the fan speed by 1.
     * Handles RemoteException if there is an issue with the remote service.
     */
    public void decreaseFanSpeed() {
        try {
            if (fanSpeedControlService != null) {
                fanSpeedControlService.decreaseFanSpeed();
            }
        } catch (RemoteException e) {
            Log.e("FanSpeedClient", "RemoteException", e);
        }
    }

    /**
     * Turns the fan on.
     * Handles RemoteException if there is an issue with the remote service.
     */
    public void turnFanOn() {
        try {
            if (fanSpeedControlService != null) {
                fanSpeedControlService.turnFanOn();
            }
        } catch (RemoteException e) {
            Log.e("FanSpeedClient", "RemoteException", e);
        }
    }

    /**
     * Turns the fan off.
     * Handles RemoteException if there is an issue with the remote service.
     */
    public void turnFanOff() {
        try {
            if (fanSpeedControlService != null) {
                fanSpeedControlService.turnFanOff();
            }
        } catch (RemoteException e) {
            Log.e("FanSpeedClient", "RemoteException", e);
        }
    }

    /**
     * Retrieves the current fan speed.
     * Handles RemoteException if there is an issue with the remote service.
     *
     * @return the current fan speed, or 0 if the service is not connected.
     */
    public int getFanSpeed() {
        try {
            if (fanSpeedControlService != null) {
                return fanSpeedControlService.getFanSpeed();
            }
        } catch (RemoteException e) {
            Log.e("FanSpeedClient", "RemoteException", e);
        }
        return 0;
    }
}
