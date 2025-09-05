package yb.kinomaptestandroid.badges.presentation.categories

import yb.kinomaptestandroid.badges.domain.badges.BadgeCategory

sealed class CategoriesUIState {

    object Initial : CategoriesUIState()
    object Loading : CategoriesUIState()
    data class Success(val data: List<BadgeCategory>) : CategoriesUIState()
    data class Failure(val message: String) : CategoriesUIState()

}