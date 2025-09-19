package hurta.matej.adventure_challenge.feature.date.presentation.list

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import hurta.matej.adventure_challenge.feature.date.domain.Date
import hurta.matej.adventure_challenge.ui.theme.cursiveFontFamily
import kotlin.collections.setOf

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
private fun DateCard(
    date: Date,
    onClick: () -> Unit,
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
            DateListItem(date = date)
        }
    }
}

@Composable
private fun DateListItem(date: Date){
    when(date.state) {
        Date.State.Locked -> DateListItemLocked(date)
        Date.State.Unopened -> DateListItemUnopened(date)
        Date.State.Opened -> DateListItemOpened(date)
    }
}

@Composable
private fun DateListItemLocked(date: Date) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(18.dp, 18.dp, 18.dp, 0.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            shape = RoundedCornerShape(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Gray),
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
        modifier = Modifier.padding(18.dp, 18.dp, 18.dp, 0.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            shape = RoundedCornerShape(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Gray),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.QuestionMark,
                        contentDescription = "Locked date",
                        modifier = Modifier.size(48.dp),
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(0.dp, 12.dp).fillMaxWidth(0.35f),
                        thickness = 2.dp,
                        color = Color.Black)
                    Row {
                        for (flag in date.flags) {
                            Icon(
                                imageVector = flag.getIcon(),
                                contentDescription = "Date flag",
                                modifier = Modifier.size(32.dp),
                            )
                        }
                    }
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
        Spacer(modifier = Modifier.height(20.dp))
        EmptyLinesForFutureRemark()
    }
}

@Composable
private fun DateListItemOpened(date: Date) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(18.dp, 18.dp, 18.dp, 0.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            shape = RoundedCornerShape(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Gray),
        ) {
            if(date.photoPresent == false){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.AddAPhoto,
                        contentDescription = "Locked date",
                        modifier = Modifier.size(48.dp),
                    )
                }
            }else{
                // photo input
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

        if(date.userRemark.isEmpty()){
            Spacer(modifier = Modifier.height(20.dp))
            EmptyLinesForFutureRemark()
        }else{
            Spacer(modifier = Modifier.height(12.dp))
            Column {
                val lines = wrapTextToLines(date.userRemark)
                val maxLines = 3

                for (i in 0 until maxLines) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (i < lines.size) {
                            Text(
                                text = lines[i],
                                fontFamily = cursiveFontFamily,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(26.dp)
                                    .zIndex(1f)
                            )
                        } else {
                            Text(
                                text = "",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(26.dp)
                                    .zIndex(1f)
                            )
                        }

                        if (i < 3) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(0.dp, 0.dp, 0.dp, 3.dp,)
                                    .zIndex(0f),
                                thickness = 2.dp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }

    }
}

private fun wrapTextToLines(text: String, maxCharsPerLine: Int = 50): List<String> {
    val cleanText = text.replace("\n", " ")
    val words = cleanText.split(" ")
    val lines = mutableListOf<String>()
    var currentLine = ""

    for (word in words) {
        if (currentLine.isEmpty()) {
            currentLine = word
        } else if (currentLine.length + 1 + word.length <= maxCharsPerLine) {
            currentLine += " $word"
        } else {
            lines.add(currentLine)
            currentLine = word
        }
    }

    if (currentLine.isNotEmpty()) {
        lines.add(currentLine)
    }

    return lines
}

@Composable
private fun EmptyLinesForFutureRemark(){
    Column {
        HorizontalDivider(
            modifier = Modifier.padding(0.dp, 12.dp),
            thickness = 2.dp,
            color = Color.Gray)
        HorizontalDivider(
            modifier = Modifier.padding(0.dp, 12.dp),
            thickness = 2.dp,
            color = Color.Gray)
        HorizontalDivider(
            modifier = Modifier.padding(0.dp, 12.dp),
            thickness = 2.dp,
            color = Color.Gray)
    }

}

@Composable
private fun DateListTopInfo(date: Date){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = "$: ${date.costMin}-${date.costMax}",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.AccessTime,
                contentDescription = "Start time",
                modifier = Modifier.size(16.dp),
                tint = Color.Gray
            )
            Text(
                text = ": ${date.startTime}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.HourglassEmpty,
                contentDescription = "Duration",
                modifier = Modifier.size(16.dp),
                tint = Color.Gray
            )
            Text(
                text = ": ${date.durationMinHours}-${date.durationMaxHours}h",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }
    }
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
                flags = setOf(Date.DateFlag.Outdoors, Date.DateFlag.Babysitter, Date.DateFlag.Active),
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
                flags = setOf(Date.DateFlag.Outdoors, Date.DateFlag.Babysitter, Date.DateFlag.Active),
                userRemark = "Twas a nice holiday we have ddidnt we. oi yes sir we did Did we alright \n fuck me for fucks sake ai man I really like your collra bone",
                photoPresent = false,
                category = Date.Category.TheBeginning,
                state = Date.State.Opened,
                stage = 1
            ),
            onClick = {}
        )
    }
}
