import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "hurta.matej.adventure_challenge"
    compileSdk = 35

    defaultConfig {
        applicationId = "hurta.matej.adventure_challenge"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JvmTarget.JVM_11.target
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.android.core)

    implementation(libs.android.lifecycle.runtime)
    implementation(libs.android.lifecycle.runtime.compose)
    implementation(libs.android.lifecycle.viewmodel.compose)

    implementation(libs.android.navigation.compose)

    implementation(libs.android.room)
    ksp(libs.android.room.compiler)

    implementation(libs.coil.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.activity)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    debugImplementation(libs.compose.uiTooling)
    implementation(libs.compose.uiToolingPreview)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.android.compose)

    implementation(libs.kotlin.serialization.json)

    testImplementation(libs.test.junit)

}
