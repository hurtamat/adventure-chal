package hurta.matej.adventure_challenge.core.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hurta.matej.adventure_challenge.feature.date.presentation.list.ListScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Start,
    ) {
        composable<Screen.Start> {
            StartScreen(navController)
        }

        composable<Screen.List> {
            ListScreen(navController)
        }
    }
}

@Composable
private fun StartScreen(navController: NavHostController) {
    // Placeholder start screen - you can implement this later
    androidx.compose.material3.Button(
        onClick = { navController.navigate(Screen.List) }
    ) {
        androidx.compose.material3.Text("Start Adventure!")
    }
}
