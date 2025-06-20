package com.leandrocourse.libraries.arch.koin.koinlazy

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.leandrocourse.libraries.arch.koin.koinload.KoinLoad
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

/**
 * Initializes Koin modules lazily in a Compose environment.
 *
 * This function uses the `rememberKoinModules` composable to manage the lifecycle of Koin modules
 * based on the provided parameters. It allows for conditional unloading of modules when they are
 * no longer needed.
 *
 * @param koinLoad An instance of [KoinLoad] that provides the modules to be loaded.
 * @param unloadOnForgotten Optional parameter to specify if modules should be unloaded when they
 * are forgotten by the Compose runtime. Defaults to `null`.
 * @param unloadOnAbandoned Optional parameter to specify if modules should be unloaded when they
 * are abandoned by the Compose runtime. Defaults to `null`.
 * @param unloadModules A boolean indicating whether modules should be unloaded explicitly. Defaults
 * to `false`.
 */

@SuppressLint("ComposableNaming")
@OptIn(KoinExperimentalAPI::class)
@Composable
fun KoinLazyModuleInitializer(
    koinLoad: KoinLoad,
    unloadOnForgotten: Boolean? = null,
    unloadOnAbandoned: Boolean? = null,
    unloadModules: Boolean = false
) {
    rememberKoinModules(
        unloadOnForgotten = unloadOnForgotten,
        unloadOnAbandoned = unloadOnAbandoned,
        unloadModules = unloadModules,
        modules = { koinLoad.modules }
    )
}
