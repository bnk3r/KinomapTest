package yb.kinomaptestandroid.badges.presentation.categories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.R
import yb.kinomaptestandroid.badges.domain.badges.BadgeFilterType
import yb.kinomaptestandroid.badges.domain.badges.BadgesFilters

@Composable
fun FiltersBadgeStatus(
    modifier: Modifier = Modifier,
    filters: BadgesFilters,
    onFiltersChanged: (BadgesFilters) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.category_screen_filters_badge_status_title)
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            BadgeFilterType.entries.forEach { filterType ->
                FilterChip(
                    selected = filters.badgeStatus == filterType,
                    onClick = { onFiltersChanged(filters.copy(badgeStatus = filterType)) },
                    label = {
                        Text(
                            text = stringResource(
                                when (filterType) {
                                    BadgeFilterType.ALL -> R.string.category_screen_filters_badge_status_all
                                    BadgeFilterType.UNLOCKED -> R.string.category_screen_filters_badge_status_unlocked
                                    BadgeFilterType.LOCKED -> R.string.category_screen_filters_badge_status_locked
                                    BadgeFilterType.COMPLETE -> R.string.category_screen_filters_badge_status_complete
                                    BadgeFilterType.UNFINISHED -> R.string.category_screen_filters_badge_status_unfinished
                                }
                            )
                        )
                    }
                )
            }
        }
    }
}