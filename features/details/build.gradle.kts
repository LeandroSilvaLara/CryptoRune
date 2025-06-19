plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}
apply("$rootDir/plugins/android-build.gradle")

android {
    namespace = "${Config.NAMESPACE}.features.details"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Modules
    implementation(project(Modules.arch))
    implementation(project(Modules.domain))
    implementation(project(Modules.remote))
    implementation(project(Modules.navigation))
    implementation(project(Modules.design))

    // Libs

    // TestLibs
    testImplementation(testFixtures(project(Modules.testing)))
    androidTestImplementation(testFixtures(project(Modules.testing)))
    testImplementation(libs.okhttp)
    testImplementation(libs.logging.interceptor)
}
