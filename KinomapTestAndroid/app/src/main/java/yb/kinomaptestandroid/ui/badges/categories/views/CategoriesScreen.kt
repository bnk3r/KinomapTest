package yb.kinomaptestandroid.ui.badges.categories.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import yb.kinomaptestandroid.ui.navigation.models.BadgeCategory

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    categories: List<BadgeCategory>?
) {
    when (categories) {
        null -> {
            Text(
                modifier = modifier,
                textAlign = TextAlign.Center,
                text = "Loading..."
            )
        }

        else -> {
            LazyColumn(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                categories.forEach { category ->
                    item {
                        CategoryItemView(
                            modifier = Modifier.fillMaxWidth(),
                            category = category
                        )
                    }
                    category.badges.forEach { badge ->
                        item {
                            BadgeItemView(
                                modifier = Modifier.fillMaxWidth(),
                                badge = badge
                            )
                        }
                    }
                }
            }
        }
    }
}