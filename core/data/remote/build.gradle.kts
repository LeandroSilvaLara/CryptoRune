plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.secrets.gradle.plugin)
}
apply("$rootDir/plugins/android-build.gradle")

secrets {
    propertiesFileName = "$rootDir/secrets/apiKey.properties"
    defaultPropertiesFileName = "$rootDir/default.properties"
}

android {
    namespace = "${Config.NAMESPACE}.core.data.remote"

    defaultConfig {
        buildConfigField(
            type = "String",
            name = "APP_VERSION",
            value = "\"${Config.VERSION_NAME}\""
        )
    }
    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"http://rest.coinapi.io/\""
            )
        }
        configureEach {
            if (name == "staging" || name == "release") {
                buildConfigField(
                    type = "String",
                    name = "BASE_URL",
                    value = "\"https://rest.coinapi.io/\""
                )
            }
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // Modules
    implementation(project(Modules.arch))
    implementation(project(Modules.domain))
    implementation(project(Modules.design))

    // Libs
    api(libs.retrofit)
    api(libs.converter.gson)
    api(libs.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.annotation)

    // TestLibs
    testImplementation(testFixtures(project(Modules.testing)))
}
