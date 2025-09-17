package hurta.matej.adventure_challenge.feature.date.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import hurta.matej.adventure_challenge.feature.date.domain.Date

@Entity(tableName = "date")
data class DbDate(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val durationMin: Int,
    val durationMax: Int,
    val cost: Int,
    val startTime: String,
    val flags: Set<Date.DateFlag> = emptySet(),
    val userRemark: String,
    val photoPresent: Boolean,
    val category: Date.Category,
    val State: Date.State,
    val Stage: Int,
)
