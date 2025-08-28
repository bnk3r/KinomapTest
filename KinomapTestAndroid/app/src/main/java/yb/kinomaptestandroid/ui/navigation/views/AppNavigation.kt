package yb.kinomaptestandroid.ui.navigation.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import yb.kinomaptestandroid.ui.badges.details.views.BadgesDetailsScreen
import yb.kinomaptestandroid.ui.badges.categories.views.CategoriesScreen
import yb.kinomaptestandroid.ui.navigation.controllers.AppNavigationCtrl
import yb.kinomaptestandroid.ui.navigation.models.BadgesDetailsScreenNav
import yb.kinomaptestandroid.ui.navigation.models.BadgesListScreenNav

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    appNavCtrl: AppNavigationCtrl = koinViewModel()
) {
    val navCtrl = rememberNavController()
    val badgeData = appNavCtrl.badgeData.collectAsStateWithLifecycle().value

    NavHost(
        navController = navCtrl,
        startDestination = BadgesListScreenNav,
        modifier = modifier
    ) {

        composable<BadgesListScreenNav> {
            CategoriesScreen(
                modifier = Modifier.fillMaxSize(),
                categories = badgeData?.categories
            )
        }

        composable<BadgesDetailsScreenNav> {
            BadgesDetailsScreen(
                modifier = Modifier.fillMaxSize(),
                navToList = { appNavCtrl.navToBadgesList(navCtrl) }
            )
        }

    }
}