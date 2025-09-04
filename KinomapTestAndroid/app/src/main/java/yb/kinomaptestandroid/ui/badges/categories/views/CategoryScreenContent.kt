package yb.kinomaptestandroid.ui.badges.categories.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import yb.kinomaptestandroid.ui.navigation.models.BadgeCategory

@Composable
fun CategoryScreenContent(
    modifier: Modifier = Modifier,
    categories: List<BadgeCategory>,
    onClickBadgeView: (id: Int) -> Unit
) {
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    val gridColumns = when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> 2
        else -> 5
    }

    val isFirstBadgeVisible =
        remember {
            derivedStateOf {
                if (listState.layoutInfo.visibleItemsInfo.isEmpty()) true
                else listState.layoutInfo.visibleItemsInfo[0].index <= gridColumns + 1
            }
        }

    if (categories.isNotEmpty()) {
        Box(
            modifier = modifier
        ) {
            LazyVerticalGrid(
                modifier = modifier.padding(horizontal = 16.dp),
                columns = GridCells.Fixed(gridColumns),
                state = listState
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

            if (!isFirstBadgeVisible.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    FloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(index = 0)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}