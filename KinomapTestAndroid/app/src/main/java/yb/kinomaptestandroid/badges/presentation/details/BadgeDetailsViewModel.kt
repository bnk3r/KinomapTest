package yb.kinomaptestandroid.badges.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yb.kinomaptestandroid.badges.data.api.KinomapService
import yb.kinomaptestandroid.badges.domain.badges.Badge

class BadgeDetailsViewModel(
    val kinomapService: KinomapService
) : ViewModel() {

    private val _uiState = MutableStateFlow<BadgeDetailsUiState>(BadgeDetailsUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun fetchBadge(badgeId: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { BadgeDetailsUiState.Loading }
                val responseBadge = kinomapService.getTechTestData().data
                    .flatMap { it.badges }
                    .find { it.id == badgeId }
                _uiState.update {
                    when (responseBadge) {
                        null -> BadgeDetailsUiState.Failure
                        else -> BadgeDetailsUiState.Success(
                            Badge(
                                id = responseBadge.id,
                                name = responseBadge.name,
                                desc = responseBadge.description,
                                category = responseBadge.category,
                                unlockedDateEpochTime = responseBadge.unlockedDate,
                                unlockedPercent = responseBadge.unlockedPercent,
                                unlockedImgUrl = responseBadge.imagesUrl.unlocked,
                                lockedImgUrl = responseBadge.imagesUrl.locked
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e(
                    "BADGE_DETAILS",
                    "Error fetching data (message=\"${e.message ?: "_unknown_"}\""
                )
                _uiState.update { BadgeDetailsUiState.Failure }
            }
        }
    }

}