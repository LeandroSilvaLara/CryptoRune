package com.leandrocourse.cryptorune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.leandrocourse.cryptorune.presentation.MainNavGraph
import com.leandrocourse.libraries.arch.di.FEATURE_ROUTES_QUALIFIER
import com.leandrocourse.libraries.arch.navigation.extension.NavHostControllerProvider
import com.leandrocourse.libraries.design.theme.PlutoTheme
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            PlutoTheme(
                statusBarColor = Color.Transparent,
                navigationBarColor = Color.Transparent,
            ) {
                Surface {
                    NavHostControllerProvider { navController ->
                        MainNavGraph(
                            navController = navController,
                            featureRoutes = koinInject(named(FEATURE_ROUTES_QUALIFIER))
                        )
                    }
                }
            }
        }
    }
}
