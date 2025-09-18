package hurta.matej.adventure_challenge.feature.date.data

import hurta.matej.adventure_challenge.feature.date.domain.Date
import kotlinx.coroutines.flow.first

class DateSeeder(private val dateRepository: DateRepository) {

    suspend fun seedDatabase() {
        // Check if database is already seeded
        if (dateRepository.getAllDatesStream().first().isNotEmpty()) {
            return
        }

        val seedDates = getSeedDates()
        dateRepository.synchronizeDates(seedDates)
    }

    private fun getSeedDates(): List<Date> {
        return listOf(
            Date(
                title = "Shape Thrifters",
                description = "Go to a thrift store and follow these rules:\nBuy your partner an outfit of your choosing (min. 3 items).\nWith your new outfits on, give each other a new name that you will use the rest of the evening.\nDo a public activity (mini golf movie, eating dessert).\nHave a stranger take a picture of you guys with your camera.",
                durationMinHours = 2,
                durationMaxHours = 5,
                costMin = 15,
                costMax = 30,
                startTime = "Before 7:00 PM",
                flags = emptySet(),
                userRemark = "",
                photoPresent = false,
                category = Date.Category.TheBeginning,
                state = Date.State.Unopened,
                stage = 1
            ),
            Date(
                title = "I Have Dollars In My Pocket",
                description = "Both of you go to different dollar stores and spend no more than $5 each on a gift/gifts that best represent your significant other. Don't spend more than 15 minutes searching in the store. Go back home, sit back-to-back and discreetly wrap the gifts. (Wrap them as nicely as you can.)\nGive your gifts to each other. Explain why those gifts represent the other and how much they mean to you.",
                durationMinHours = 2,
                durationMaxHours = 3,
                costMin = 10,
                costMax = 10,
                startTime = "Before 9:00 PM",
                flags = emptySet(),
                userRemark = "",
                photoPresent = false,
                category = Date.Category.TheBeginning,
                state = Date.State.Unopened,
                stage = 1
            ),
            Date(
                title = "Vinyl",
                description = "Go to a vintage music store. Find the coolest vinyl album cover ever, regardless of whether you've heard of the band or not.\nLook up the history & backstory of the album artwork and the artist, while listening to the vinyl at the record shop.\nGo to a cozy coffee/ cocktail bar stream one of the songs and talk about what you think the lyrics mean.",
                durationMinHours = 2,
                durationMaxHours = 3,
                costMin = 15,
                costMax = 30,
                startTime = "Before 7:00 PM",
                flags = emptySet(),
                userRemark = "",
                photoPresent = false,
                category = Date.Category.TheBeginning,
                state = Date.State.Unopened,
                stage = 1
            )
        )
    }
}