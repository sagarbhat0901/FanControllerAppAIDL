package com.example.fancontrollerapp;

/**
 * Utility class for interfacing with the native fan controller.
 */
public class NativeUtils {

    static {
        // Load the native library 'fancontrollerapp' which contains the native methods
        System.loadLibrary("fancontrollerapp");
    }

    /**
     * Increases the fan speed by 1, up to a maximum of 5, if the fan is currently on.
     *
     * @return true if the fan speed was increased, false otherwise.
     */
    public static native boolean increaseFanSpeedHal();

    /**
     * Decreases the fan speed by 1, down to a minimum of 0, if the fan is currently on.
     *
     * @return true if the fan speed was decreased, false otherwise.
     */
    public static native boolean decreaseFanSpeedHal();

    /**
     * Turns the fan on if it is currently off.
     *
     * @return true if the fan was turned on, false otherwise.
     */
    public static native boolean turnFanOnHal();

    /**
     * Turns the fan off if it is currently on.
     *
     * @return true if the fan was turned off, false otherwise.
     */
    public static native boolean turnFanOffHal();

    /**
     * Retrieves the current fan speed.
     *
     * @return the current fan speed if the fan is on, otherwise 0.
     */
    public static native int getFanSpeedHal();

    /**
     * Checks whether the fan is currently on.
     *
     * @return true if the fan is on, false otherwise.
     */
    public static native boolean isFanOnHal();
}
