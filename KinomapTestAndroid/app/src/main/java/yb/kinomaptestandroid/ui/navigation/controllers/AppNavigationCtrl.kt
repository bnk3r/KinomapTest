package yb.kinomaptestandroid.ui.navigation.controllers

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.PopUpToBuilder
import androidx.navigation.navOptions
import yb.kinomaptestandroid.ui.navigation.models.BadgesDetailsScreenNav
import yb.kinomaptestandroid.ui.navigation.models.BadgesListScreenNav

class AppNavigationCtrl : ViewModel() {

    fun navToBadgesDetails(
        navCtrl: NavHostController
    ) {
        navCtrl.navigate(BadgesDetailsScreenNav)
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