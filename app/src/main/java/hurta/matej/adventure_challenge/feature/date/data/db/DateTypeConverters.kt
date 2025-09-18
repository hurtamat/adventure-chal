package hurta.matej.adventure_challenge.feature.date.data.db

import androidx.room.TypeConverter
import hurta.matej.adventure_challenge.feature.date.domain.Date

class DateTypeConverters {

    @TypeConverter
    fun fromDateFlagSet(flags: Set<Date.DateFlag>): String {
        return flags.joinToString(",") { it.name }
    }

    @TypeConverter
    fun toDateFlagSet(flagsString: String): Set<Date.DateFlag> {
        if (flagsString.isBlank()) return emptySet()
        return flagsString.split(",").mapNotNull { flagName ->
            try {
                Date.DateFlag.valueOf(flagName.trim())
            } catch (e: IllegalArgumentException) {
                null
            }
        }.toSet()
    }

    @TypeConverter
    fun fromCategory(category: Date.Category): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(categoryString: String): Date.Category {
        return Date.Category.valueOf(categoryString)
    }

    @TypeConverter
    fun fromState(state: Date.State): String {
        return state.name
    }

    @TypeConverter
    fun toState(stateString: String): Date.State {
        return Date.State.valueOf(stateString)
    }
}