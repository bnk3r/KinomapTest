package yb.kinomaptestandroid.badges.presentation.categories.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import yb.kinomaptestandroid.badges.domain.badges.BadgeCategory
import yb.kinomaptestandroid.badges.domain.badges.BadgesFilters

@Composable
fun CategoriesSuccess(
    modifier: Modifier = Modifier,
    categories: List<BadgeCategory>,
    filters: BadgesFilters,
    onClickBadgeView: (badgeId: Int) -> Unit,
    onFiltersChanged: (BadgesFilters) -> Unit
) {
    var isFilterPanelVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CategoriesHeader(
                modifier = Modifier.fillMaxWidth(),
                onClickFilters = { isFilterPanelVisible = true }
            )
            CategoriesContent(
                modifier = Modifier.fillMaxSize(),
                categories = categories,
                onClickBadgeView = onClickBadgeView
            )
        }

        FiltersOverlay(
            modifier = Modifier.fillMaxSize(),
            visible = isFilterPanelVisible,
            filters = filters,
            onFiltersChanged = onFiltersChanged,
            onClickHide = { isFilterPanelVisible = false }
        )
    }
}