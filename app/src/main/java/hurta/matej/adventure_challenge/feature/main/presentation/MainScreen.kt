package hurta.matej.adventure_challenge.feature.main.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import hurta.matej.adventure_challenge.core.presentation.ui.Navigation

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Navigation(
        navController = navController,
        modifier = Modifier.fillMaxSize()
    )
}
