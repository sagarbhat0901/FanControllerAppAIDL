plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.fancontrollerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fancontrollerapp"
        minSdk = 28
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

    /*implementation("androidx.car.app:app:1.7.0-alpha02")
    implementation("androidx.car.app:app:1.7.0-alpha02")
    implementation("androidx.car.app:app-automotive:1.7.0-alpha02")
    testImplementation("androidx.car.app:app-testing:1.7.0-alpha02")*/

   /* implementation ("androidx.car.app:app:1.1.0")
    implementation ("androidx.car.app:app-projected:1.1.0")


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)*/
    implementation ("androidx.appcompat:appcompat:1.3.1")
   // implementation ("com.google.android.material:material:1.4.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation ("androidx.car.app:app:1.1.0")
    implementation ("androidx.car.app:app-projected:1.1.0")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")


    implementation ("com.google.android.material:material:1.7.0")
}