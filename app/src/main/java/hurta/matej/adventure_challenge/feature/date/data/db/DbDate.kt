package hurta.matej.adventure_challenge.feature.date.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import hurta.matej.adventure_challenge.feature.date.domain.Date

@Entity(tableName = "date")
data class DbDate(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val durationMinHours: Int,
    val durationMaxHours: Int,
    val costMin: Int,
    val costMax: Int,
    val startTime: String,
    val flags: Set<Date.DateFlag> = emptySet(),
    val userRemark: String,
    val photoPresent: Boolean,
    val category: Date.Category,
    val State: Date.State,
    val Stage: Int,
)
