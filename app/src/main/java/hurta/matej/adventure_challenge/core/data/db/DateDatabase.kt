package hurta.matej.adventure_challenge.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hurta.matej.adventure_challenge.feature.date.data.db.DateDao
import hurta.matej.adventure_challenge.feature.date.data.db.DateTypeConverters
import hurta.matej.adventure_challenge.feature.date.data.db.DbDate

@Database(
    version = 1,
    entities = [DbDate::class],
    exportSchema = false
)
@TypeConverters(DateTypeConverters::class)
abstract class DateDatabase : RoomDatabase() {

    abstract fun dateDao(): DateDao

    companion object {

        fun newInstance(context: Context): DateDatabase {
            return Room.databaseBuilder(context, DateDatabase::class.java, "date.db").build()
        }
    }
}
