package hurta.matej.adventure_challenge.feature.date.presentation.list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hurta.matej.adventure_challenge.R
import hurta.matej.adventure_challenge.feature.date.domain.Date

@Composable
fun DatesList(
    dates: List<Date>,
    modifier: Modifier = Modifier,
    onDateClick: (Date) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dates, key = { it.title }) { date ->
            DateCard(
                date = date,
                onClick = { onDateClick(date) },
            )
        }
    }
}

@Composable
private fun DateCard(
    date: Date,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        DateListItem(date = date)
    }
}

@Composable
private fun DateListItem(date: Date) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_characters), // Placeholder icon
            contentDescription = "Date icon",
            modifier = Modifier
                .size(80.dp)
                .padding(16.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = date.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.weight(1f),
                )
                if (date.photoPresent) {
                    Spacer(Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorites_filled),
                        contentDescription = "Photo available",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            Text(
                text = date.category.getTitle(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )

            Text(
                text = "${date.durationMin}-${date.durationMax} min • $${date.cost}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}
