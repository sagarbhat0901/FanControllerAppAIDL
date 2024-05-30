package com.example.fancontrollerapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class FanSpeedClient {

    private IFanSpeedControlService fanSpeedControlService;
    private boolean isServiceConnected = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            fanSpeedControlService = IFanSpeedControlService.Stub.asInterface(service);
            isServiceConnected = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            fanSpeedControlService = null;
            isServiceConnected = false;
        }
    };

    public void bindService(Context context) {
        Intent intent = new Intent(context, FanSpeedControlService.class);
        intent.setPackage("com.example.fancontroller");
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unbindService(Context context) {
        if (isServiceConnected) {
            context.unbindService(serviceConnection);
            isServiceConnected = false;
        }
    }

    public void increaseFanSpeed() {
        try {
            if (fanSpeedControlService != null) {
                fanSpeedControlService.increaseFanSpeed();
            }
        } catch (RemoteException e) {
            Log.e("FanSpeedClient", "RemoteException", e);
        }
    }

    public void decreaseFanSpeed() {
        try {
            if (fanSpeedControlService != null) {
                fanSpeedControlService.decreaseFanSpeed();
            }
        } catch (RemoteException e) {
            Log.e("FanSpeedClient", "RemoteException", e);
        }
    }

    public void turnFanOn() {
        try {
            if (fanSpeedControlService != null) {
                fanSpeedControlService.turnFanOn();
            }
        } catch (RemoteException e) {
            Log.e("FanSpeedClient", "RemoteException", e);
        }
    }

    public void turnFanOff() {
        try {
            if (fanSpeedControlService != null) {
                fanSpeedControlService.turnFanOff();
            }
        } catch (RemoteException e) {
            Log.e("FanSpeedClient", "RemoteException", e);
        }
    }

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

