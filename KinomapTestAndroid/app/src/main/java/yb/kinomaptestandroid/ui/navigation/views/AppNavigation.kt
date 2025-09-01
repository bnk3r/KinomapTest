package yb.kinomaptestandroid.ui.navigation.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import yb.kinomaptestandroid.ui.app.controllers.ConnectivityController
import yb.kinomaptestandroid.ui.badges.categories.views.CategoriesScreen
import yb.kinomaptestandroid.ui.badges.details.views.BadgesDetailsScreen
import yb.kinomaptestandroid.ui.navigation.controllers.AppNavigationCtrl
import yb.kinomaptestandroid.ui.navigation.models.BadgesDetailsScreenNav
import yb.kinomaptestandroid.ui.navigation.models.BadgesListScreenNav

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    appNavCtrl: AppNavigationCtrl = koinViewModel(),
    connectivityController: ConnectivityController = koinViewModel()
) {
    val navCtrl = rememberNavController()
    val isNetworkConnected = connectivityController.isConnected.collectAsStateWithLifecycle().value
    val badgeData = appNavCtrl.badgeData.collectAsStateWithLifecycle().value
    val badges = appNavCtrl.badges.collectAsStateWithLifecycle().value

    LaunchedEffect(isNetworkConnected) {
        if (isNetworkConnected && badgeData == null) {
            appNavCtrl.fetchTestData()
        }
    }

    NavHost(
        navController = navCtrl,
        startDestination = BadgesListScreenNav,
        modifier = modifier
    ) {

        composable<BadgesListScreenNav> {
            CategoriesScreen(
                modifier = Modifier.fillMaxSize(),
                categories = badgeData?.categories,
                onClickBadgeView = { badgeId ->
                    appNavCtrl.navToBadgesDetails(
                        navCtrl = navCtrl,
                        badgeId = badgeId
                    )
                }
            )
        }

        composable<BadgesDetailsScreenNav> {
            it.arguments?.let { args ->
                val badgeId = args.getInt("badgeId", -1)
                if (badgeId == -1) return@let
                if (badges == null) return@let
                val badge = badges.find { b -> b.id == badgeId }
                if (badge == null) return@let
                BadgesDetailsScreen(
                    modifier = Modifier.fillMaxSize(),
                    badge = badge
                )
            }
        }

    }
}