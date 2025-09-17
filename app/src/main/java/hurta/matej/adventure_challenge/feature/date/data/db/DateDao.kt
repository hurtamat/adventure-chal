package hurta.matej.adventure_challenge.feature.date.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DateDao {

    @Query("SELECT * FROM date")
    abstract fun getAllStream(): Flow<List<DbDate>>

    @Query("SELECT * FROM date WHERE id = :id")
    abstract fun getDateStream(id: Int): Flow<DbDate?>

    @Transaction
    open suspend fun synchronize(dates: List<DbDate>) {
        deleteAll()
        insert(dates)
    }

    @Query("DELETE FROM date")
    protected abstract suspend fun deleteAll()

    @Insert
    protected abstract suspend fun insert(dates: List<DbDate>)

    @Update
    abstract suspend fun update(date: DbDate)
}
