package hurta.matej.adventure_challenge.feature.main.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hurta.matej.adventure_challenge.core.presentation.ui.Navigation
import hurta.matej.adventure_challenge.core.presentation.ui.Screen

@Composable
fun MainScreen() {
    Column {
        val navController = rememberNavController()

        Navigation(navController = navController, modifier = Modifier.weight(1f))
        val currentEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentEntry?.destination
        val shouldShowBottomNavigation = currentDestination?.hasBottomNavigation() ?: false
        if (shouldShowBottomNavigation) {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
                Screen.TopLevel.all.forEach { screen ->
                    NavigationBarItem(
                        painter = painterResource(id = screen.icon),
                        name = stringResource(screen.title),
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(screen::class) } == true,
                        onClick = {
                            if (!currentDestination.isScreen(screen)) {
                                navController.navigate(screen) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

private fun NavDestination.hasBottomNavigation(): Boolean {
    val bottomNavigationDestinations = listOf(
        Screen.TopLevel.Characters,
    )
    return bottomNavigationDestinations.any { hasRoute(it::class) }
}

@Composable
private fun RowScope.NavigationBarItem(
    painter: Painter,
    name: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (selected) {
        MaterialTheme.colorScheme.outlineVariant
    } else {
        MaterialTheme.colorScheme.onTertiary
    }

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        icon = {
            Icon(painter = painter, contentDescription = null, tint = contentColor)
        },
        label = {
            Text(text = name, style = MaterialTheme.typography.labelMedium, color = contentColor)
        }
    )
}

private fun NavDestination?.isScreen(screen: Screen): Boolean {
    if (this == null) return false

    return hasRoute(screen::class)
}
