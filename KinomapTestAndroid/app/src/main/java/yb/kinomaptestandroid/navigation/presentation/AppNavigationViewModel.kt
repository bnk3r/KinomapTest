package yb.kinomaptestandroid.navigation.presentation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import yb.kinomaptestandroid.navigation.domain.destinations.BadgesDetailsScreenNav
import yb.kinomaptestandroid.navigation.domain.destinations.BadgesListScreenNav

class AppNavigationViewModel : ViewModel() {

    fun navToBadgesDetails(
        navCtrl: NavHostController,
        badgeId: Int
    ) {
        navCtrl.navigate(BadgesDetailsScreenNav(badgeId = badgeId)) {
            launchSingleTop = true
        }
    }

    fun navToBadgesList(
        navCtrl: NavHostController
    ) {
        navCtrl.navigate(BadgesListScreenNav, navOptions {
            popUpTo<BadgesListScreenNav> {
                inclusive = true
            }
        })
    }

}