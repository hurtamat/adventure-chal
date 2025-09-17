package hurta.matej.adventure_challenge.core.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hurta.matej.adventure_challenge.feature.character.presentation.list.ListScreen
import hurta.matej.adventure_challenge.feature.character.presentation.search.SearchScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.TopLevel.Characters,
    ) {
        composable<Screen.TopLevel.Characters> {
            ListScreen(navController)
        }

        composable<Screen.Search> {
            SearchScreen(navController)
        }
    }
}
