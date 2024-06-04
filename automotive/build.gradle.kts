plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.fancontrollerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fancontrollerapp"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        aidl=true;
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

dependencies {

    //implementation(libs.appcompat)
    implementation(libs.material)
    //implementation(libs.activity)
    //implementation(libs.constraintlayout)
    implementation ("androidx.car.app:app:1.7.0-alpha02")
    implementation("androidx.car.app:app-automotive:1.7.0-alpha02")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    testImplementation("androidx.car.app:app-testing:1.7.0-alpha02")
}