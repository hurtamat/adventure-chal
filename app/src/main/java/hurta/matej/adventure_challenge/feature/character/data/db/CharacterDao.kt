package hurta.matej.adventure_challenge.feature.character.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CharacterDao {

    @Query("SELECT * FROM character")
    abstract fun getAllStream(): Flow<List<DbCharacter>>

    @Query("SELECT * FROM character WHERE id = :id")
    abstract fun getCharacterStream(id: Int): Flow<DbCharacter?>

    @Transaction
    open suspend fun synchronize(characters: List<DbCharacter>) {
        deleteAll()
        insert(characters)
    }

    @Query("DELETE FROM character")
    protected abstract suspend fun deleteAll()

    @Insert
    protected abstract suspend fun insert(characters: List<DbCharacter>)
    
    @Update
    abstract suspend fun update(character: DbCharacter)
}
