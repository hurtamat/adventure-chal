package hurta.matej.adventure_challenge.feature.date.presentation.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import hurta.matej.adventure_challenge.R
import hurta.matej.adventure_challenge.feature.date.domain.Date
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = koinViewModel(),
) {
    val screenState by viewModel.screenStateStream.collectAsStateWithLifecycle()

    DetailScreen(
        screenState = screenState,
        onPhotoClick = viewModel::onFavoriteClick,
        onNavigateBackClick = { navController.popBackStack() },
    )
}

@Composable
private fun DetailScreen(
    screenState: DetailScreenState,
    onPhotoClick: () -> Unit,
    onNavigateBackClick: () -> Unit,
) {
    val date = screenState.date
    Scaffold(
        topBar = {
            DetailTopBar(
                date = date,
                onNavigateBackClick = onNavigateBackClick,
                onPhotoClick = onPhotoClick,
            )
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            if (date == null) {
                Text(
                    text = "Date details not available",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                )
            } else {
                DateDetail(date)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopBar(
    date: Date?,
    onNavigateBackClick: () -> Unit,
    onPhotoClick: () -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigateBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.back),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        },
        title = {
            Text(
                text = date?.title.orEmpty(),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        actions = {
            IconButton(onClick = onPhotoClick) {
                Icon(
                    painterResource(id = R.drawable.ic_favorites),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Photo",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Composable
private fun DateDetail(date: Date) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            DateCardHeader(date)
            Spacer(modifier = Modifier.height(16.dp))
            DateCardDetails(date)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun DateCardHeader(date: Date) {
    Row(
        modifier = Modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            shape = RoundedCornerShape(16.dp)
        )
    ) {
        AsyncImage(
            model = "", // Date doesn't have imageUrl
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentDescription = "Image",
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
        ) {
            Text(
                text = stringResource(R.string.name),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = date.title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
private fun DateCardDetails(date: Date) {
    Column(
        modifier = Modifier
            .fillMaxWidth(fraction = 2 / 3f)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Information(title = "Description", value = date.description)
        Information(title = "Duration", value = "${date.durationMin}-${date.durationMax} min")
        Information(title = "Cost", value = "$${date.cost}")
        Information(title = "Start Time", value = date.startTime)
        Information(title = "Category", value = date.category.getTitle())
        Information(title = "Stage", value = date.Stage.toString())
    }
}

@Composable
private fun Information(title: String, value: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
        )
        Text(
            text = value.takeUnless { it.isBlank() } ?: "-",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
