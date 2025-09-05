package yb.kinomaptestandroid.badges.presentation.categories.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.R

@Composable
fun CategoriesHeader(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(vertical = 16.dp),
        text = stringResource(R.string.category_screen_title),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge
    )
}