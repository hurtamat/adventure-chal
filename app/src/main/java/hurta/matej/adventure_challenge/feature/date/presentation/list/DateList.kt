package hurta.matej.adventure_challenge.feature.date.presentation.list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hurta.matej.adventure_challenge.R
import hurta.matej.adventure_challenge.feature.date.domain.Date

@Composable
fun DatesList(
    datesByStage: Map<Int, List<Date>>,
    modifier: Modifier = Modifier,
    onDateClick: (Date) -> Unit,
) {
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
                    onClick = { onDateClick(date) },
                )
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
private fun DateCard(
    date: Date,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (date.state == Date.State.Locked) Color.Black else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        DateListItem(date = date)
    }
}

@Composable
private fun DateListItem(date: Date){
    when(date.state) {
        Date.State.Locked -> DateListItemLocked(date)
        Date.State.Unopened -> DateListItemUnopened(date)
        Date.State.Opened -> DateListItemOpened(date)
        Date.State.Completed -> DateListItemCompleted(date)
    }
}

@Composable
private fun DateListItemLocked(date: Date) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4f),
            shape = RoundedCornerShape(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Gray),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Locked date",
                    modifier = Modifier.size(48.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = date.title,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DateListItemUnopened(date: Date) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4f),
            shape = RoundedCornerShape(2.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Icon(
                        imageVector = Icons.Filled.QuestionMark,
                        contentDescription = "Locked date",
                        modifier = Modifier.size(48.dp),
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = date.title,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DateListItemOpened(date: Date) {
}

@Composable
private fun DateListItemCompleted(date: Date) {

}

@Preview(showBackground = true)
@Composable
private fun DateCardLockedPreview() {
    MaterialTheme {
        DateCard(
            date = Date(
                title = "Shape Thrifters",
                description = "Go to a thrift store and follow these rules...",
                durationMinHours = 2,
                durationMaxHours = 5,
                costMin = 15,
                costMax = 30,
                startTime = "Before 7:00 PM",
                flags = emptySet(),
                userRemark = "",
                photoPresent = false,
                category = Date.Category.TheBeginning,
                state = Date.State.Locked,
                stage = 1
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DateCardUnopenedPreview() {
    MaterialTheme {
        DateCard(
            date = Date(
                title = "I Have Dollars In My Pocket",
                description = "Both of you go to different dollar stores...",
                durationMinHours = 2,
                durationMaxHours = 3,
                costMin = 10,
                costMax = 10,
                startTime = "Before 9:00 PM",
                flags = emptySet(),
                userRemark = "",
                photoPresent = false,
                category = Date.Category.TheBeginning,
                state = Date.State.Unopened,
                stage = 1
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DateCardOpenedPreview() {
    MaterialTheme {
        DateCard(
            date = Date(
                title = "Vinyl",
                description = "Go to a vintage music store...",
                durationMinHours = 2,
                durationMaxHours = 3,
                costMin = 15,
                costMax = 30,
                startTime = "Before 7:00 PM",
                flags = emptySet(),
                userRemark = "",
                photoPresent = false,
                category = Date.Category.TheBeginning,
                state = Date.State.Opened,
                stage = 1
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DateCardCompletedPreview() {
    MaterialTheme {
        DateCard(
            date = Date(
                title = "Shape Thrifters",
                description = "Go to a thrift store and follow these rules...",
                durationMinHours = 2,
                durationMaxHours = 5,
                costMin = 15,
                costMax = 30,
                startTime = "Before 7:00 PM",
                flags = emptySet(),
                userRemark = "Amazing experience!",
                photoPresent = true,
                category = Date.Category.TheBeginning,
                state = Date.State.Completed,
                stage = 1
            ),
            onClick = {}
        )
    }
}
