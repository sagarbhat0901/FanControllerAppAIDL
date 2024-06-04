package com.example.fancontrollerapp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HalTesting {
    static {
        // Load the native library 'fancontrollerapp' which contains the native methods
        System.loadLibrary("fancontrollerapp");
    }
    private int fanSpeed;
    private int fanSpeed1;
    private boolean isFanOn;

    @Test
    public void testIncreaseFanSpeed() {

        // get initial fan speed
        fanSpeed = NativeUtils.getFanSpeedHal();

        // increase fan speed
        NativeUtils.increaseFanSpeedHal();

        //get current fan speed
        fanSpeed1 = NativeUtils.getFanSpeedHal();

        // Verify result and fan speed
        assertEquals(fanSpeed, fanSpeed1);
    }

    @Test
    public void testDecreaseFanSpeed() {
        //get initial fan speed
        fanSpeed = NativeUtils.getFanSpeedHal();

        // decrease fan speed
        NativeUtils.decreaseFanSpeedHal();

        //get current fan speed
        fanSpeed1 = NativeUtils.getFanSpeedHal();

        // Verify fan speed
        assertEquals(fanSpeed, fanSpeed1);
    }

    @Test
    public void testFanTurnedOff() {
        // Turn the fan off
        NativeUtils.turnFanOffHal();

        // Check if the fan is off
        isFanOn = NativeUtils.isFanOnHal();

        // Verify that the fan is off after turning it off
        assertEquals(false, isFanOn);
    }

    @Test
    public void testTurnFanOnHal() {

        // Turn the fan on
        NativeUtils.turnFanOnHal();

        // Check if the fan is on
        isFanOn = NativeUtils.isFanOnHal();

        // Verify that the fan is on after turning it on
        assertEquals(true, isFanOn);
    }

}
