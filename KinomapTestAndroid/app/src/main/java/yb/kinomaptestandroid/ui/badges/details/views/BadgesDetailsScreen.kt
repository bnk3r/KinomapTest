package yb.kinomaptestandroid.ui.badges.details.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BadgesDetailsScreen(
    modifier: Modifier = Modifier,
    navToList: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = navToList
        ) {
            Text("List")
        }
    }
}