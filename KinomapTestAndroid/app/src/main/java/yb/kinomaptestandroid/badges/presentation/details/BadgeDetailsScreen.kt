package yb.kinomaptestandroid.badges.presentation.details

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import yb.kinomaptestandroid.badges.presentation.details.components.BadgeDetailsLandscape
import yb.kinomaptestandroid.badges.presentation.details.components.BadgeDetailsPortrait
import yb.kinomaptestandroid.ui.domain.network.InternetStatus
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun BadgeDetailsScreen(
    modifier: Modifier = Modifier,
    badgesDetailsViewModel: BadgeDetailsViewModel = koinViewModel(),
    internetStatus: InternetStatus,
    badgeId: Int
) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation
    val uiState = badgesDetailsViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(internetStatus, uiState) {
        if (internetStatus == InternetStatus.ONLINE && uiState.value is BadgeDetailsUiState.Initial) {
            badgesDetailsViewModel.fetchBadge(badgeId)
        }
    }

    when (val state = uiState.value) {
        is BadgeDetailsUiState.Initial -> {
            // TODO
        }

        is BadgeDetailsUiState.Loading -> {
            // TODO
        }

        is BadgeDetailsUiState.Success -> {
            when (orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    BadgeDetailsPortrait(
                        modifier = modifier,
                        badge = state.badge,
                        unlockedDate = state.badge.readableDate,
                        badgeOwned = state.badge.isComplete
                    )
                }

                else -> {
                    BadgeDetailsLandscape(
                        modifier = modifier,
                        badge = state.badge,
                        unlockedDate = state.badge.readableDate,
                        badgeOwned = state.badge.isComplete
                    )
                }
            }
        }

        is BadgeDetailsUiState.Failure -> {
            // TODO
        }
    }
}