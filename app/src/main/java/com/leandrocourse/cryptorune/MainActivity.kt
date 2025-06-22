package com.leandrocourse.cryptorune

import android.os.Bundle
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.leandrocourse.cryptorune.application.MainViewModel
import com.leandrocourse.cryptorune.presentation.MainNavGraph
import com.leandrocourse.libraries.arch.di.FEATURE_ROUTES_QUALIFIER
import com.leandrocourse.libraries.arch.navigation.extension.NavHostControllerProvider
import com.leandrocourse.libraries.design.theme.PlutoTheme
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoading.value
        }
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val iconView = splashScreenViewProvider.iconView
            val splashScreenView = splashScreenViewProvider.view
            iconView.animate()
                .scaleX(0f)
                .scaleY(0f)
                .alpha(0f)
                .setDuration(500L)
                .setInterpolator(AnticipateInterpolator())
                .withEndAction {
                    splashScreenViewProvider.remove()
                }
                .start()

            splashScreenView.animate()
                .alpha(0f)
                .setDuration(500L)
                .start()
        }

        enableEdgeToEdge()

        setContent {
            val isLoading by viewModel.isLoading.collectAsState()

            PlutoTheme(
                statusBarColor = Color.Transparent,
                navigationBarColor = Color.Transparent,
            ) {
                Surface {
                    // O NavGraph só é renderizado quando o carregamento termina
                    if (!isLoading) {
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
}