package hurta.matej.adventure_challenge.feature.date.domain

data class Date(
    val title: String,
    val description: String,
    val durationMin: Int,
    val durationMax: Int,
    val cost: Int,
    val startTime: String,
    val flags: Set<Date.DateFlag> = emptySet(),
    val userRemark: String,
    val photoPresent: Boolean,
    val category: Date.Category,
    val Stage: Int,
) {


    enum class DateFlag {
        Meal, Snacks, Mess, GetWet, Supplies, Active,
        Indoors, Outdoors, Home, Away, Daylight, Nighttime, Filming, Babysitter, PlanAhead;

        fun getTitle(): String {
            return when (this) {
                Meal -> "Meal"
                Snacks -> "Snacks"
                Mess -> "Mess"
                GetWet -> "Get Wet"
                Supplies -> "Supplies"
                Active -> "Active"
                Indoors -> "Indoors"
                Outdoors -> "Outdoors"
                Home -> "At Home"
                Away -> "Away"
                Daylight -> "Daylight"
                Nighttime -> "Nighttime"
                Filming -> "Filming"
                Babysitter -> "Babysitter Needed"
                PlanAhead -> "Plan Ahead"
            }
        }

        fun getDescription(): String {
            return when (this) {
                Meal -> "Make sure to come with a hungry stomach! This symbol indicates that the challenge will involve eating a full meal."
                Snacks -> "Be prepared to enjoy a prepared or purchased snack at some point during the challenge."
                Mess -> "Sometimes adventures get messy, but it's worth it! The adventures with this symbol will likely result in needing to wash your clothes afterward."
                GetWet -> "Prepare for the splash zone! These activities might get wet, so dress appropriately."
                Supplies -> "Unless you have a large stock of specific foods or random craft supplies at your home, the challenges with this icon may require you to get some essential items from the store."
                Active -> "These adventures will have a higher level of physical exertion. Nothing will be too hard, but they might make you sweat or run around a bit more than the others."
                Indoors -> "Takes place indoors....good ones for a rainy or cold day."
                Outdoors -> "Best completed outdoors; wait for a nice day to scratch this off."
                Home -> "Check for this icon if you want to do an adventure that is completed in or around your house."
                Away -> "You will probably need to go somewhere other than your house to complete this adventure. (It may be a park, lake, ice cream shop, etc.)"
                Daylight -> "You'll want to do these during daylight. Similar to the outdoor adventures, this symbol will help you decide what time of day you should complete this activity. These will be pretty tough in the dark."
                Nighttime -> "Try these adventures after dark! They are most fun after the sun has gone down."
                Filming -> "These adventures need either a photo or video camera to complete. Of course your smartphone will work!"
                Babysitter -> "Many of the adventures can be done with kids around, but these will be quite difficult to accomplish without a babysitter. Check for this indicator if you have a sitter lined up for the night."
                PlanAhead -> "This adventure will require a little planning, scratch off at least 48hrs ahead of time."
            }
        }
    }

    enum class Category {
        TheBeginning, FoodWithBAE, YouWontFindThisOnADatingBlog, HotterThanBAEPart1,
        HotterThanBAEPart2, ImCrazyAboutYou, CreativeCouple, TrustFall,
        PleaseBeTHATCouple, DoubleDates, The10DollarDates, CheapAss, TheEnd;

        fun getTitle(): String {
            return when (this) {
                TheBeginning -> "The Beginning"
                FoodWithBAE -> "Food with BAE"
                YouWontFindThisOnADatingBlog -> "You Won't Find This on a Dating Blog"
                HotterThanBAEPart1 -> "Hotter Than BAE Part 1"
                HotterThanBAEPart2 -> "Hotter Than BAE Part 2"
                ImCrazyAboutYou -> "I'm Crazy About You"
                CreativeCouple -> "Creative Couple"
                TrustFall -> "Trust Fall"
                PleaseBeTHATCouple -> "Please Be THAT Couple"
                DoubleDates -> "Double Dates"
                The10DollarDates -> "The $10 Dates"
                CheapAss -> "Cheap A$$"
                TheEnd -> "The End"
            }
        }

        fun getDescription(): String {
            return when (this) {
                TheBeginning -> "Welcome to the beginning"
                FoodWithBAE -> "Dates that involve meals"
                YouWontFindThisOnADatingBlog -> "Random, creative dates that are a bit absurd"
                HotterThanBAEPart1 -> "Warm, summer day dates"
                HotterThanBAEPart2 -> "Warm, summer night dates"
                ImCrazyAboutYou -> "Dates that dive deep into your date's mind and emotions"
                CreativeCouple -> "Dates that involve art or creativity"
                TrustFall -> "Just what it sounds like"
                PleaseBeTHATCouple -> "Cute, fun, and maybe a tad unexpected dates for THAT couple"
                DoubleDates -> "Grab another couple, and don't scratch off the date till they arrive"
                The10DollarDates -> "Well, if the title doesn't explain it, I don't know what else to say"
                CheapAss -> "Ya Broke?"
                TheEnd -> "The last chapter of your story"
            }
        }
    }
}
