package yb.kinomaptestandroid.badges.presentation.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import yb.kinomaptestandroid.badges.presentation.categories.components.CategoriesContent
import yb.kinomaptestandroid.badges.presentation.categories.components.CategoriesHeader
import yb.kinomaptestandroid.badges.presentation.categories.components.CategoriesLoading
import yb.kinomaptestandroid.ui.domain.network.InternetStatus

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    categoriesViewModel: CategoriesViewModel = koinViewModel(),
    internetStatus: InternetStatus,
    onClickBadgeView: (id: Int) -> Unit
) {

    val uiState = categoriesViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(internetStatus, uiState) {
        if (internetStatus == InternetStatus.ONLINE && uiState.value is CategoriesUIState.Initial) {
            categoriesViewModel.fetchCategories()
        }
    }

    when (val state = uiState.value) {
        is CategoriesUIState.Initial -> {
            // TODO
        }

        is CategoriesUIState.Loading -> {
            CategoriesLoading(
                modifier = modifier
            )
        }

        is CategoriesUIState.Success -> {
            Column(
                modifier = modifier
            ) {
                CategoriesHeader(
                    modifier = Modifier.fillMaxWidth()
                )
                CategoriesContent(
                    modifier = modifier,
                    categories = state.data,
                    onClickBadgeView = onClickBadgeView
                )
            }
        }

        is CategoriesUIState.Failure -> {
            // TODO
        }
    }
}