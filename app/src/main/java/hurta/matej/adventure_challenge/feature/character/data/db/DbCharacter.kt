package hurta.matej.adventure_challenge.feature.character.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class DbCharacter(
    @PrimaryKey val id: Int,
    val name: String,
    val statusId: Int,
    val species: String,
    val type: String,
    val genderId: Int,
    val origin: String,
    val location: String,
    val imageUrl: String,
    val favorite: Boolean,
)
