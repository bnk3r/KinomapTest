package yb.kinomaptestandroid.badges.presentation.categories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import yb.kinomaptestandroid.badges.domain.badges.BadgesFilters

@Composable
fun FiltersOverlay(
    modifier: Modifier = Modifier,
    visible: Boolean,
    filters: BadgesFilters,
    onFiltersChanged: (BadgesFilters) -> Unit,
    onClickHide: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        SemiTransparentBackground(
            modifier = Modifier.fillMaxSize(),
            visible = visible,
            onClick = onClickHide
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            FilterPanel(
                modifier = Modifier.fillMaxWidth(),
                visible = visible,
                filters = filters,
                onFiltersChanged = onFiltersChanged,
                onClickClose = onClickHide
            )
        }
    }
}