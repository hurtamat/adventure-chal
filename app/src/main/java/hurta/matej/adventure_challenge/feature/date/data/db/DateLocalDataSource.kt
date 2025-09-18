package hurta.matej.adventure_challenge.feature.date.data.db

import hurta.matej.adventure_challenge.feature.date.domain.Date
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DateLocalDataSource(private val dateDao: DateDao) {

    fun getAllStream(): Flow<List<Date>> {
        return dateDao.getAllStream().map { dbDates ->
            dbDates.map { it.toDomain() }
        }
    }

    private fun DbDate.toDomain(): Date {
        return Date(
            id = id,
            title = title,
            description = description,
            durationMinHours = durationMinHours,
            durationMaxHours = durationMaxHours,
            costMin = costMin,
            costMax = costMax,
            startTime = startTime,
            flags = flags,
            userRemark = userRemark,
            photoPresent = photoPresent,
            category = category,
            state = State,
            stage = Stage,
        )
    }

    fun getDateStream(id: Int): Flow<Date?> {
        return dateDao.getDateStream(id).map { it?.toDomain() }
    }

    suspend fun synchronize(dates: List<Date>) {
        dateDao.synchronize(dates.toDb())
    }

    private fun List<Date>.toDb(): List<DbDate> {
        return map { date -> date.toDb() }
    }

    private fun Date.toDb(): DbDate {
        return DbDate(
            id = if (id == 0) 0 else id, // Let Room auto-generate if id is 0
            title = title,
            description = description,
            durationMinHours = durationMinHours,
            durationMaxHours = durationMaxHours,
            costMin = costMin,
            costMax = costMax,
            startTime = startTime,
            flags = flags,
            userRemark = userRemark,
            photoPresent = photoPresent,
            category = category,
            State = state,
            Stage = stage,
        )
    }

    suspend fun update(date: Date) {
        dateDao.update(date.toDb())
    }
}
