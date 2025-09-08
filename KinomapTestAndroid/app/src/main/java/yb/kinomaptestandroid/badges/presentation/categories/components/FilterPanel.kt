package yb.kinomaptestandroid.badges.presentation.categories.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.badges.domain.badges.BadgesFilters

@Composable
fun FilterPanel(
    modifier: Modifier = Modifier,
    visible: Boolean,
    filters: BadgesFilters,
    onFiltersChanged: (BadgesFilters) -> Unit,
    onClickClose: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it }
        ) + fadeIn(),
        exit = slideOutVertically() + fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                )
                .padding(16.dp)
        ) {
            FiltersHeader(
                modifier = Modifier.fillMaxWidth(),
                onClickClose = onClickClose
            )
            FiltersBadgeStatus(
                modifier = Modifier.fillMaxWidth(),
                filters = filters,
                onFiltersChanged = onFiltersChanged
            )
        }
    }
}