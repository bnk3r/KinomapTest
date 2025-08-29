package yb.kinomaptestandroid.ui.badges.categories.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.R
import yb.kinomaptestandroid.ui.navigation.models.BadgeCategory

const val GRID_COLUMNS = 2

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    categories: List<BadgeCategory>?,
    onClickBadgeView: (id: Int) -> Unit
) {
    when (categories) {
        null -> {
            Box(
                modifier = modifier, contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            LazyVerticalGrid(
                modifier = modifier.padding(horizontal = 16.dp),
                columns = GridCells.Fixed(GRID_COLUMNS)
            ) {
                item(
                    span = { GridItemSpan(GRID_COLUMNS) }) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        text = stringResource(R.string.category_screen_title),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                categories.forEachIndexed { i, category ->
                    if (i != 0) {
                        item(
                            span = { GridItemSpan(GRID_COLUMNS) }
                        ) {
                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )
                        }
                    }
                    item(
                        span = { GridItemSpan(GRID_COLUMNS) }
                    ) {
                        CategoryItemView(
                            modifier = Modifier.fillMaxWidth(), category = category
                        )
                    }
                    if (category.badges.isNotEmpty()) {
                        item(
                            span = { GridItemSpan(GRID_COLUMNS) }
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
    }
}