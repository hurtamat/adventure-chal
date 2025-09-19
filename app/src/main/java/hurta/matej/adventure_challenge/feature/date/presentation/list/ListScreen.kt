package hurta.matej.adventure_challenge.feature.date.presentation.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import hurta.matej.adventure_challenge.R
import hurta.matej.adventure_challenge.core.presentation.ui.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListViewModel = koinViewModel(),
) {
    val screenState by viewModel.screenStateStream.collectAsStateWithLifecycle()

    ListScreen(
        screenState = screenState,
        onDateClick = { date ->
            navController.navigate(Screen.DatesDetail(date.id))
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListScreen(
    screenState: ListScreenState,
    onDateClick: (hurta.matej.adventure_challenge.feature.date.domain.Date) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) { paddingValues ->
        DatesList(
            datesByStage = screenState.datesByStage,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            onDateClick = onDateClick,
        )
    }
}
