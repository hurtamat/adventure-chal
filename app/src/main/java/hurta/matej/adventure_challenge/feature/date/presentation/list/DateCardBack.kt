package hurta.matej.adventure_challenge.feature.date.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import hurta.matej.adventure_challenge.feature.date.domain.Date
import hurta.matej.adventure_challenge.ui.theme.cursiveFontFamily

@Composable
fun DateCardBack(
    date: Date,
) {
    Column(
    ) {
        Card(
            modifier = Modifier
                .aspectRatio(3f / 4f),
            shape = RoundedCornerShape(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (date.state == Date.State.Locked) Color.Black else MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            DateCardBackContent(date = date)
        }
    }
}

@Composable
private fun DateCardBackContent(date: Date) {
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
            val lines = date.description.split("\n")
            Box(
                modifier = Modifier.fillMaxSize().padding(12.dp),
                contentAlignment = Alignment.Center
            ){
                Column(
                    horizontalAlignment = AbsoluteAlignment.Left
                ) {
                    for(line in lines){
                        Text(
                            text = line,
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
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
        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(0.40f),
            thickness = 2.dp,
            color = Color.Gray
        )
        Row {
            for (flag in date.flags) {
                Icon(
                    imageVector = flag.getIcon(),
                    contentDescription = flag.getTitle(),
                    modifier = Modifier.size(32.dp).padding(horizontal = 2.dp),
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun DateCardOpenedPreview() {
    MaterialTheme {
        DateCardBack(
            date = Date(
                title = "Vinyl",
                description = "Go to a thrift store and follow these rules:\nBuy your partner an outfit of your choosing (min. 3 items).\nWith your new outfits on, give each other a new name that you will use the rest of the evening.\nDo a public activity (mini golf movie, eating dessert).\nHave a stranger take a picture of you guys with your camera.",
                durationMinHours = 2,
                durationMaxHours = 3,
                costMin = 15,
                costMax = 30,
                startTime = "Before 7:00 PM",
                flags = setOf(Date.DateFlag.Outdoors, Date.DateFlag.Babysitter, Date.DateFlag.Active),
                userRemark = "Twas 3 a nice holiday we have ddidnt we. oi yes sir we did Did we alright \n fuck me for fucks sake ai man I really like your collra bone",
                photoPresent = false,
                category = Date.Category.TheBeginning,
                state = Date.State.Opened,
                stage = 1
            ),
        )
    }
}