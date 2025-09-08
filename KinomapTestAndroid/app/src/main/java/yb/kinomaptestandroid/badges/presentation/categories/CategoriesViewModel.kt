package yb.kinomaptestandroid.badges.presentation.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yb.kinomaptestandroid.badges.data.api.KinomapService
import yb.kinomaptestandroid.badges.domain.badges.Badge
import yb.kinomaptestandroid.badges.domain.badges.BadgeCategory
import yb.kinomaptestandroid.badges.domain.badges.BadgeFilterType
import yb.kinomaptestandroid.badges.domain.badges.BadgesFilters

class CategoriesViewModel(
    val kinomapService: KinomapService
) : ViewModel() {

    private val _uiState = MutableStateFlow<CategoriesUIState>(CategoriesUIState.Initial)
    val uiState = _uiState.asStateFlow()

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                _uiState.update { CategoriesUIState.Loading }
                val data = kinomapService.getTechTestData().data.map { data ->
                    BadgeCategory(
                        name = data.name,
                        badges = data.badges.map { badge ->
                            Badge(
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
                    )
                }
                _uiState.update { CategoriesUIState.Success(data) }
            } catch (e: Exception) {
                Log.e("CATEGORIES", "Error fetching data (message=\"${e.message ?: "_unknown_"}\"")
                _uiState.update { CategoriesUIState.Failure }
            }
        }
    }

    fun retryFetchingCategories() {
        viewModelScope.launch {
            _uiState.update { CategoriesUIState.Initial }
        }
    }

    fun filteredCategories(): List<BadgeCategory> {
        return when (val state = uiState.value) {
            is CategoriesUIState.Success -> {
                state.data.map { category ->
                    category.copy(
                        badges = when (state.filters.badgeStatus) {
                            BadgeFilterType.ALL -> category.badges
                            BadgeFilterType.UNLOCKED -> category.badges.filter { it.isUnlocked }
                            BadgeFilterType.LOCKED -> category.badges.filterNot { it.isUnlocked }
                            BadgeFilterType.COMPLETE -> category.badges.filter { it.isComplete }
                            BadgeFilterType.UNFINISHED -> category.badges.filterNot { it.isComplete }
                        }
                    )
                }
            }

            else -> emptyList()
        }
    }

    fun updateFilters(filters: BadgesFilters) {
        if (uiState.value !is CategoriesUIState.Success) return
        viewModelScope.launch {
            _uiState.update {
                it as CategoriesUIState.Success
                CategoriesUIState.Success(
                    data = it.data,
                    filters = filters
                )
            }
        }
    }

}