package hurta.matej.adventure_challenge.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hurta.matej.adventure_challenge.feature.date.data.db.DateDao
import hurta.matej.adventure_challenge.feature.date.data.db.DbCharacter

@Database(version = 1, entities = [DbCharacter::class])
abstract class DateDatabase : RoomDatabase() {

    abstract fun characterDao(): DateDao

    companion object {

        fun newInstance(context: Context): DateDatabase {
            return Room.databaseBuilder(context, DateDatabase::class.java, "date.db").build()
        }
    }
}
