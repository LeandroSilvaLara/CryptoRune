plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.kotlin.ksp)
}
apply("$rootDir/plugins/android-build.gradle")

android {
    namespace = "${Config.NAMESPACE}.core.data.local"

    defaultConfig {
        buildConfigField(
            type = "String",
            name = "DATABASE_NAME",
            value = "\"challenge.db\""
        )
    }
    buildFeatures {
        buildConfig = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    // Modules
    implementation(project(Modules.arch))
    implementation(project(Modules.domain))

    // Libs
    implementation(libs.annotation)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // TestLibs
    testImplementation(testFixtures(project(Modules.testing)))
    androidTestImplementation(testFixtures(project(Modules.testing)))
}
ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
    arg("room.incremental", "true")
    arg("room.generateKotlin", "true")
}
