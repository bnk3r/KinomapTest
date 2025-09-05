package yb.kinomaptestandroid.badges.presentation.categories.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import yb.kinomaptestandroid.R

@Composable
fun CategoriesFailure(
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.Companion.size(96.dp),
            imageVector = Icons.Default.Info,
            contentDescription = null,
            colorFilter = ColorFilter.Companion.tint(MaterialTheme.colorScheme.error)
        )
        Spacer(modifier = Modifier.Companion.height(16.dp))
        Text(
            text = stringResource(R.string.category_screen_failure_unknown),
        )
        Spacer(modifier = Modifier.Companion.height(48.dp))
        Button(
            onClick = onClickRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        ) {
            Text(
                text = stringResource(R.string.category_screen_failure_retry).uppercase(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}