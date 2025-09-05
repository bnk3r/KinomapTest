package yb.kinomaptestandroid.badges.presentation.details

import yb.kinomaptestandroid.badges.domain.badges.Badge

sealed class BadgeDetailsUiState {

    object Initial: BadgeDetailsUiState()
    object Loading: BadgeDetailsUiState()
    data class Success(val badge: Badge): BadgeDetailsUiState()
    data class Failure(val message: String): BadgeDetailsUiState()

}