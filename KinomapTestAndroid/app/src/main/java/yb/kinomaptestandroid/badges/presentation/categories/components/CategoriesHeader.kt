package yb.kinomaptestandroid.badges.presentation.categories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.R

@Composable
fun CategoriesHeader(
    modifier: Modifier = Modifier,
    onClickFilters: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.category_screen_title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedIconButton(
            modifier = Modifier.size(32.dp),
            onClick = onClickFilters,
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Outlined.FilterAlt,
                contentDescription = null
            )
        }
    }
}