package yb.kinomaptestandroid.ui.badges.categories.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.R
import yb.kinomaptestandroid.ui.navigation.models.BadgeCategory

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
            Column(
                modifier = modifier
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    text = stringResource(R.string.category_screen_title),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )

                CategoryScreenContent(
                    modifier = modifier,
                    categories = categories,
                    onClickBadgeView = onClickBadgeView
                )
            }
        }
    }
}