package yb.kinomaptestandroid.ui.badges.categories.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.ui.navigation.models.BadgeCategory

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