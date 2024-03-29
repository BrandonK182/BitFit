plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id ("org.jetbrains.kotlin.plugin.serialization")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.bitfit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bitfit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        dataBinding = true
        viewBinding = true
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation ("androidx.room:room-runtime:2.4.2")
    implementation ("androidx.room:room-ktx:2.4.2")
    annotationProcessor ("androidx.room:room-compiler:2.4.2")
    kapt ("androidx.room:room-compiler:2.4.2")
    implementation ("androidx.fragment:fragment-ktx:1.5.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.23")
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.codepath.libraries:asynchttpclient:2.2.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
}