plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.to_dolist"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.to_dolist"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation (libs.recyclerview.swipedecorator)
    implementation (libs.recyclerview.swipedecorator)
    implementation ("it.xabaras.android:recyclerview-swipedecorator:1.4")

    implementation(libs.constraintlayout)
    testImplementation(libs.junit)

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

dependencies {



    implementation ("it.xabaras.android:recyclerview-swipedecorator:1.4")
}