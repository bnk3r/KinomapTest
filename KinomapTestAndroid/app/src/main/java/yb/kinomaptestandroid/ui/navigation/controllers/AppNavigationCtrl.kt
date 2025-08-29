package yb.kinomaptestandroid.ui.navigation.controllers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yb.kinomaptestandroid.data.models.Badge
import yb.kinomaptestandroid.data.models.Data
import yb.kinomaptestandroid.data.models.TestData
import yb.kinomaptestandroid.data.services.KinomapService
import yb.kinomaptestandroid.ui.navigation.models.BadgeCategory
import yb.kinomaptestandroid.ui.navigation.models.BadgeData
import yb.kinomaptestandroid.ui.navigation.models.BadgesDetailsScreenNav
import yb.kinomaptestandroid.ui.navigation.models.BadgesListScreenNav

class AppNavigationCtrl(
    val kinomapService: KinomapService,
) : ViewModel() {

    private val _badgeData: MutableStateFlow<BadgeData?> = MutableStateFlow(null)
    val badgeData: StateFlow<BadgeData?> = _badgeData.asStateFlow()

    private val _badges: MutableStateFlow<List<yb.kinomaptestandroid.ui.navigation.models.Badge>?> =
        MutableStateFlow(null)
    val badges: StateFlow<List<yb.kinomaptestandroid.ui.navigation.models.Badge>?> =
        _badges.asStateFlow()

    init {
        fetchTestData()
    }

    fun fetchTestData() {
        viewModelScope.launch {
            val data = kinomapService.getTechTestData().toBadgeData()
            _badgeData.update { data }
            _badges.update { data.categories.flatMap { it.badges } }
        }
    }

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

    private fun TestData.toBadgeData(): BadgeData =
        BadgeData(
            categories = this.data.toCategories()
        )

    private fun List<Data>.toCategories(): List<BadgeCategory> =
        map { data ->
            BadgeCategory(
                name = data.name,
                badges = data.badges.toViewBadges()
            )
        }

    private fun List<Badge>.toViewBadges(): List<yb.kinomaptestandroid.ui.navigation.models.Badge> =
        map { badge ->
            yb.kinomaptestandroid.ui.navigation.models.Badge(
                id = badge.id,
                name = badge.name,
                desc = badge.description,
                category = badge.category,
                unlockedDateEpochTime = badge.unlockedDate,
                unlockedPercent = badge.unlockedPercent,
                unlockedImgUrl = badge.imagesUrl.unlocked,
                lockedImgUrl = badge.imagesUrl.locked
            )
        }
}