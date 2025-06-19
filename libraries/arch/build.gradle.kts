plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}
apply("$rootDir/plugins/android-build.gradle")

android {
    namespace = "${Config.NAMESPACE}.libraries.arch"

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Modules

    // Libs
    api(libs.koin.core)
    api(libs.koin.android)
    api(libs.koin.androidx.compose)
    api(libs.startup.runtime)
    api(libs.joda.time)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.reflect)
    implementation(libs.mockk)
    implementation(libs.work.runtime.ktx)
    implementation(libs.navigation.animation)
}
