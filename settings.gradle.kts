pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}


// App
include(":app")

rootProject.name = "CryptoRune"

include(":libraries")
include(":libraries:arch")
include(":libraries:design")
include(":libraries:testing")
include(":features")
include(":features:details")
include(":features:exchanges")
include(":core")
include(":core:domain")
include(":core:data")
include(":core:data:local")
include(":core:data:remote")
include(":core:navigation")
