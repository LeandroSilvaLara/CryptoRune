import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

apply("$rootDir/plugins/android-build.gradle")

val keystorePropertiesFile = rootProject.file("secrets/keystore.properties")

android {
    namespace = Config.NAMESPACE

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME
    }

    signingConfigs {
        if (keystorePropertiesFile.exists()) {
            val keystoreProperties = Properties()
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))

            create("signing") {
                storeFile = file(keystoreProperties["STORE_FILE"] as String)
                storePassword = keystoreProperties["STORE_PASSWORD"] as String
                keyPassword = keystoreProperties["KEY_PASSWORD"] as String
                keyAlias = keystoreProperties["KEY_ALIAS"] as String
            }
        }
    }

    buildTypes {
        named("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        named("staging") {
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            matchingFallbacks += listOf("release", "debug")
            isMinifyEnabled = true
        }
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
        configureEach {
            if (name == "staging" || name == "release") {
                if (keystorePropertiesFile.exists()) {
                    signingConfig = signingConfigs.getByName("signing")
                } else {
                    println("keystore.properties not found for $name variant, skipping signing config")
                }
            }
        }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/java")
        getByName("debug").java.srcDirs("src/debug/java")
        getByName("release").java.srcDirs("src/release/java")
        getByName("staging").java.srcDirs("src/staging/java")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}
dependencies {

    implementation(project(Modules.arch))
    implementation(project(Modules.design))
    implementation(project(Modules.navigation))
    implementation(project(Modules.remote))
    implementation(project(Modules.local))
    implementation(project(Modules.domain))
    implementation(project(Modules.exchanges))
    implementation(project(Modules.testing))
    implementation(project(Modules.details))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}