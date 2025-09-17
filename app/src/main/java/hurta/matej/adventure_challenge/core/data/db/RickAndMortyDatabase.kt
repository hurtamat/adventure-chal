package hurta.matej.adventure_challenge.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hurta.matej.adventure_challenge.feature.character.data.db.CharacterDao
import hurta.matej.adventure_challenge.feature.character.data.db.DbCharacter

@Database(version = 1, entities = [DbCharacter::class])
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {

        fun newInstance(context: Context): RickAndMortyDatabase {
            return Room.databaseBuilder(context, RickAndMortyDatabase::class.java, "rick-and-morty.db").build()
        }
    }
}
