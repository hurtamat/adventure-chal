package hurta.matej.adventure_challenge.feature.date.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import hurta.matej.adventure_challenge.R
import hurta.matej.adventure_challenge.core.presentation.ui.Screen
import hurta.matej.adventure_challenge.feature.date.domain.Date
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListViewModel = koinViewModel(),
) {
    val screenState by viewModel.screenStateStream.collectAsStateWithLifecycle()

    ListScreen(
        screenState = screenState,
        onAddPhotoClick = { /* TODO: Implement onAddPhotoClick */ },
        onAddRemarkClick = { /* TODO: Implement onAddRemarkClick */ },
        onUnlockDateClick = { /* TODO: Implement onUnlockDateClick */ },
        onFlipDateCardClick = { /* TODO: Implement onFlipDateCardClick */ },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListScreen(
    screenState: ListScreenState,
    onAddPhotoClick: (Date) -> Unit,
    onAddRemarkClick: (Date) -> Unit,
    onUnlockDateClick: (Date) -> Unit,
    onFlipDateCardClick: (Date) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) { paddingValues ->
        DatesList(
            datesByStage = screenState.datesByStage,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
private fun DatesList(
    datesByStage: Map<Int, List<Date>>,
    modifier: Modifier = Modifier,
) {
    val flippedCards = remember { mutableStateOf(setOf<Int>()) }
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        datesByStage.toSortedMap().forEach { (stage, dates) ->
            item(key = "stage_$stage") {
                StageDivider(stage = stage)
            }

            items(dates, key = { it.title }) { date ->
                DateCard(
                    date = date,
                    pageState = flippedCards.value.contains(date.id),
                    onClick = {
                        flippedCards.value = if (flippedCards.value.contains(date.id)) {
                            flippedCards.value - date.id // Remove if flipped (flip back to front)
                        } else {
                            flippedCards.value + date.id // Add if not flipped (flip to back)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun StageDivider(
    stage: Int,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            text = "Stage $stage",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun DateCard(
    date: Date,
    onClick: () -> Unit,
    pageState: Boolean = false, // false = front, true = back
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        if(date.state == Date.State.Unopened) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                DateListTopInfo(date)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
        Card(
            modifier = Modifier.clickable(onClick = onClick)
                .aspectRatio(3f / 4f),
            shape = RoundedCornerShape(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (date.state == Date.State.Locked) Color.Black else MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            if (pageState) {
                DateCardBack(date = date)
            } else {
                DateListItem(date = date)
            }
        }
    }
}
