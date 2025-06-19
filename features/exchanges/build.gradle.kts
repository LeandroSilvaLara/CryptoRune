plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}
apply("$rootDir/plugins/android-build.gradle")

android {
    namespace = "${Config.NAMESPACE}.features.exchanges"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Modules
    implementation(project(Modules.arch))
    //implementation(project(Modules.domain))
    //implementation(project(Modules.remote))
    //implementation(project(Modules.local))
    //implementation(project(Modules.navigation))
    implementation(project(Modules.design))

    // Libs
    implementation(libs.compose.shimmer)

    // TestLibs
    testImplementation(testFixtures(project(Modules.testing)))
    androidTestImplementation(testFixtures(project(Modules.testing)))
    testImplementation(libs.okhttp)
    testImplementation(libs.logging.interceptor)
}
