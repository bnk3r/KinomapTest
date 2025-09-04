package yb.kinomaptestandroid.ui.badges.categories.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.ui.navigation.models.BadgeCategory

@Composable
fun CategoryScreenContent(
    modifier: Modifier = Modifier,
    categories: List<BadgeCategory>,
    onClickBadgeView: (id: Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    val gridColumns = when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> 2
        else -> 5
    }

    LazyVerticalGrid(
        modifier = modifier.padding(horizontal = 16.dp),
        columns = GridCells.Fixed(gridColumns)
    ) {
        categories.forEachIndexed { i, category ->
            if (i != 0) {
                item(
                    span = { GridItemSpan(gridColumns) }
                ) {
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                }
            }
            item(
                span = { GridItemSpan(gridColumns) }
            ) {
                CategoryItemView(
                    modifier = Modifier.fillMaxWidth(), category = category
                )
            }
            if (category.badges.isNotEmpty()) {
                item(
                    span = { GridItemSpan(gridColumns) }
                ) {
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                }
            }
            category.badges.forEach { badge ->
                item {
                    BadgeItemView(
                        modifier = Modifier
                            .fillMaxWidth(),
                        badge = badge,
                        onClick = { onClickBadgeView(badge.id) })
                }
            }
        }
    }
}