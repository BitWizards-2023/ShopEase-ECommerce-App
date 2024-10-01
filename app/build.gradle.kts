plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") // Correct way to apply the Google services plugin in Kotlin DSL
}

android {
    namespace = "com.example.shopease"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.shopease"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Core dependencies for Android development
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Firebase BOM (Bill of Materials) and Messaging
    implementation(platform("com.google.firebase:firebase-bom:33.3.0")) // BOM for Firebase
    implementation("com.google.firebase:firebase-messaging") // Firebase Messaging
    implementation("com.google.firebase:firebase-analytics")

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation ("com.airbnb.android:lottie:6.5.2")
    implementation ("de.hdodenhof:circleimageview:3.1.0")

}

// Apply the Google services plugin for Firebase
apply(plugin = "com.google.gms.google-services")
