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
            title = title,
            description = description,
            durationMin = durationMin,
            durationMax = durationMax,
            cost = cost,
            startTime = startTime,
            flags = flags,
            userRemark = userRemark,
            photoPresent = photoPresent,
            category = category,
            state = State,
            Stage = Stage,
        )
    }

    fun getDateStream(id: Int): Flow<Date?> {
        return dateDao.getDateStream(id).map { it?.toDomain() }
    }

    suspend fun synchronize(dates: List<Date>) {
        dateDao.synchronize(dates.toDb())
    }

    private fun List<Date>.toDb(): List<DbDate> {
        return map { it.toDb() }
    }

    private fun Date.toDb(): DbDate {
        return DbDate(
            id = 0, // You'll need to handle ID assignment
            title = title,
            description = description,
            durationMin = durationMin,
            durationMax = durationMax,
            cost = cost,
            startTime = startTime,
            flags = flags,
            userRemark = userRemark,
            photoPresent = photoPresent,
            category = category,
            State = state,
            Stage = Stage,
        )
    }

    suspend fun update(date: Date) {
        dateDao.update(date.toDb())
    }
}
