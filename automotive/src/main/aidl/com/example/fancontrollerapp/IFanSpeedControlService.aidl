// IFanSpeedControlService.aidl
package com.example.fancontrollerapp;

// Declare any non-default types here with import statements


// Declare any non-default types here with import statements

interface IFanSpeedControlService {
    // Method to increase fan speed
    void increaseFanSpeed();

    // Method to decrease fan speed
    void decreaseFanSpeed();

    // Method to turn the fan on
    void turnFanOn();

    // Method to turn the fan off
    void turnFanOff();

    // Method to get the current fan speed
    int getFanSpeed();
}
