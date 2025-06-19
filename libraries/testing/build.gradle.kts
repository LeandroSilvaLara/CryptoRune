plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.leandrocourse.libraries.testing"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.leandrocourse.libraries.testing"
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
    kotlinOptions {
        jvmTarget = "11"
    }
}


dependencies {
    // Modules

    // Libs

    // TestLibs
    //testImplementation(libs.bundles.defaultTestLibs)
    testImplementation(libs.koin.test.junit4)
    testImplementation(libs.koin.test)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.turbine)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.coroutines.test.jvm)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.core.testing)
    testImplementation(libs.ui.test.junit4)

    // Test Fixtures dependencies
    testFixturesImplementation(libs.core.ktx)
    testFixturesImplementation(libs.room.runtime)
    //testFixturesImplementation(libs.bundles.defaultTestLibs)
    testFixturesImplementation(libs.koin.test.junit4)
    testFixturesImplementation(libs.koin.test)
    testFixturesImplementation(libs.mockk)
    testFixturesImplementation(libs.kotlin.test)
    testFixturesImplementation(libs.turbine)
    testFixturesImplementation(libs.kotlin.test.junit)
    testFixturesImplementation(libs.kotlinx.coroutines.test)
    testFixturesImplementation(libs.kotlinx.coroutines.test.jvm)
    testFixturesImplementation(libs.kotlinx.coroutines.core)
    testFixturesImplementation(libs.core.testing)
    testFixturesImplementation(libs.mockwebserver)
    testFixturesImplementation(libs.retrofit)
    testFixturesImplementation(libs.converter.gson)
    testFixturesImplementation(libs.gson)
    testFixturesImplementation(libs.okhttp)
    testFixturesImplementation(libs.logging.interceptor)
    testFixturesImplementation(libs.annotation)
    testFixturesImplementation(libs.ui.test.junit4)
    //testFixturesImplementation(project(Modules.domain))
    //testFixturesImplementation(project(Modules.arch))
}
