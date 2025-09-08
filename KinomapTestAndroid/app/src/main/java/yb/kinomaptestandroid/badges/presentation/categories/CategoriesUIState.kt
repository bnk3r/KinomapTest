package yb.kinomaptestandroid.badges.presentation.categories

import yb.kinomaptestandroid.badges.domain.badges.BadgeCategory
import yb.kinomaptestandroid.badges.domain.badges.BadgesFilters

sealed class CategoriesUIState {

    object Initial : CategoriesUIState()
    object Loading : CategoriesUIState()
    data class Success(
        val data: List<BadgeCategory>,
        val filters: BadgesFilters = BadgesFilters()
    ) : CategoriesUIState()
    object Failure : CategoriesUIState()

}