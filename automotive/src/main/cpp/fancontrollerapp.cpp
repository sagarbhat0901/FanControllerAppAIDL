// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("fancontrollerapp");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("fancontrollerapp")
//      }
//    }

#include <jni.h>

int fanSpeed = 0;
bool isFanOn = false;

extern "C" {

JNIEXPORT jboolean JNICALL
Java_com_example_fancontrollerapp_NativeUtils_increaseFanSpeedHal(
        JNIEnv* env,
        jclass javaClass) {
    if (isFanOn) {
        if (fanSpeed >= 0 && fanSpeed < 5) {
            fanSpeed ++;
            return true;
        }
    }
    return false;
}

JNIEXPORT jboolean JNICALL
Java_com_example_fancontrollerapp_NativeUtils_decreaseFanSpeedHal(
        JNIEnv* env,
        jclass javaClass) {
    if (isFanOn) {
        if (fanSpeed > 0 && fanSpeed <= 5) {
            fanSpeed --;
            return true;
        }
    }
    return false;
}

JNIEXPORT jboolean JNICALL
Java_com_example_fancontrollerapp_NativeUtils_turnFanOnHal(
        JNIEnv* env,
        jclass javaClass) {
    if (!isFanOn) {
        isFanOn = true;
        return true;
    }
    return false;
}

JNIEXPORT jboolean JNICALL
Java_com_example_fancontrollerapp_NativeUtils_turnFanOffHal(
        JNIEnv* env,
        jclass javaClass) {
    if (isFanOn) {
        isFanOn = false;
        return true;
    }
    return false;
}

JNIEXPORT jint JNICALL
Java_com_example_fancontrollerapp_NativeUtils_getFanSpeedHal(
        JNIEnv* env,
        jclass javaClass) {
    return isFanOn ? fanSpeed : 0;
}

JNIEXPORT jboolean JNICALL
Java_com_example_fancontrollerapp_NativeUtils_isFanOnHal(
        JNIEnv* env,
        jclass javaClass) {
    return isFanOn;
}

}