package hurta.matej.adventure_challenge.feature.date.data

import hurta.matej.adventure_challenge.feature.date.data.db.DateLocalDataSource
import hurta.matej.adventure_challenge.feature.date.domain.Date
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DateRepository(
    private val dateLocalDataSource: DateLocalDataSource,
) {

    fun getAllDatesStream(): Flow<List<Date>> = dateLocalDataSource.getAllStream()

    fun getDateStream(id: Int): Flow<Date?> = dateLocalDataSource.getDateStream(id)

    suspend fun updateDate(date: Date) {
        dateLocalDataSource.update(date)
    }

    suspend fun synchronizeDates(dates: List<Date>) {
        dateLocalDataSource.synchronize(dates)
    }

    suspend fun filterDates(flags: Set<Date.DateFlag>? = null, category: Date.Category? = null): List<Date> {
        return dateLocalDataSource.getAllStream().map { dates ->
            dates.filter { date ->
                val flagsMatch = flags?.let { requiredFlags ->
                    requiredFlags.all { flag -> date.flags.contains(flag) }
                } ?: true

                val categoryMatch = category?.let { requiredCategory ->
                    date.category == requiredCategory
                } ?: true

                flagsMatch && categoryMatch
            }
        }.first()
    }

    suspend fun getDatesByCategory(category: Date.Category): List<Date> {
        return filterDates(category = category)
    }

    suspend fun getDatesWithFlags(flags: Set<Date.DateFlag>): List<Date> {
        return filterDates(flags = flags)
    }
}
