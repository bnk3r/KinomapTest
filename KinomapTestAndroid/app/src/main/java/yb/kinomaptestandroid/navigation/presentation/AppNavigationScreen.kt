package yb.kinomaptestandroid.navigation.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import yb.kinomaptestandroid.badges.presentation.categories.CategoriesScreen
import yb.kinomaptestandroid.badges.presentation.details.BadgeDetailsScreen
import yb.kinomaptestandroid.navigation.domain.destinations.BadgesDetailsScreenNav
import yb.kinomaptestandroid.navigation.domain.destinations.BadgesListScreenNav
import yb.kinomaptestandroid.ui.domain.network.ConnectivityController

@Composable
fun AppNavigationScreen(
    modifier: Modifier = Modifier,
    appNavCtrl: AppNavigationViewModel = koinViewModel(),
    connectivityController: ConnectivityController = koinViewModel()
) {
    val navCtrl = rememberNavController()
    val internetStatus = connectivityController.internetStatus.collectAsStateWithLifecycle()

    NavHost(
        navController = navCtrl,
        startDestination = BadgesListScreenNav,
        modifier = modifier
    ) {

        composable<BadgesListScreenNav> {
            CategoriesScreen(
                modifier = Modifier.fillMaxSize(),
                internetStatus = internetStatus.value,
                onClickBadgeView = { badgeId ->
                    appNavCtrl.navToBadgesDetails(
                        navCtrl = navCtrl,
                        badgeId = badgeId
                    )
                }
            )
        }

        composable<BadgesDetailsScreenNav>(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            it.arguments?.let { args ->
                val badgeId = args.getInt("badgeId", -1)
                if (badgeId == -1) return@let

                BadgeDetailsScreen(
                    modifier = Modifier.fillMaxSize(),
                    badgeId = badgeId,
                    internetStatus = internetStatus.value
                )
            }
        }

    }
}