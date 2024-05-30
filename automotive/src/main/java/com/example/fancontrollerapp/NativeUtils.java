package com.example.fancontrollerapp;

public class NativeUtils {

    static {
        System.loadLibrary("fancontrollerapp");
    }

    public static native boolean increaseFanSpeedHal();
    public static native boolean decreaseFanSpeedHal();
    public static native boolean turnFanOnHal();
    public static native boolean turnFanOffHal();
    public static native int getFanSpeedHal();
}
