package yb.kinomaptestandroid.badges.presentation.categories.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.badges.domain.badges.BadgeCategory

@Composable
fun CategoryItemView(
    modifier: Modifier = Modifier,
    category: BadgeCategory
) {
    Card(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
            text = category.name,
            style = MaterialTheme.typography.titleMedium
        )
    }
}