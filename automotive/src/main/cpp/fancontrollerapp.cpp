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

// Global variables to maintain fan state
int fanSpeed = 0;
bool isFanOn = false;

extern "C" {

/**
* Increases the fan speed by 1, if the fan is on and the speed is less than 5.
*
* @param env - JNI interface pointer.
* @param javaClass - Reference to the calling Java class.
* @return true if the fan speed was increased, false otherwise.
*/
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


/**
 * Decreases the fan speed by 1, if the fan is on and the speed is greater than 0.
 *
 * @param env - JNI interface pointer.
 * @param javaClass - Reference to the calling Java class.
 * @return true if the fan speed was decreased, false otherwise.
 */
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

/**
 * Turns the fan on.
 *
 * @param env - JNI interface pointer.
 * @param javaClass - Reference to the calling Java class.
 * @return true if the fan was turned on, false otherwise.
 */
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

/**
 * Turns the fan off.
 *
 * @param env - JNI interface pointer.
 * @param javaClass - Reference to the calling Java class.
 * @return true if the fan was turned off, false otherwise.
 */
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

/**
 * Gets the current fan speed.
 *
 * @param env - JNI interface pointer.
 * @param javaClass - Reference to the calling Java class.
 * @return the current fan speed if the fan is on, 0 otherwise.
 */
JNIEXPORT jint JNICALL
Java_com_example_fancontrollerapp_NativeUtils_getFanSpeedHal(
        JNIEnv* env,
        jclass javaClass) {
    return isFanOn ? fanSpeed : 0;
}

/**
 * Checks if the fan is currently on.
 *
 * @param env - JNI interface pointer.
 * @param javaClass - Reference to the calling Java class.
 * @return true if the fan is on, false otherwise.
 */
JNIEXPORT jboolean JNICALL
Java_com_example_fancontrollerapp_NativeUtils_isFanOnHal(
        JNIEnv* env,
        jclass javaClass) {
    return isFanOn;
}

}