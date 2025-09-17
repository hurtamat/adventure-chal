package hurta.matej.adventure_challenge.feature.character.domain

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val origin: String,
    val location: String,
    val imageUrl: String,
    val favorite: Boolean,
) {

    enum class Status(val id: Int) {

        Unknown(0),
        Dead(1),
        Alive(2);

        companion object {

            fun getById(id: Int): Status {
                return entries.find { it.id == id } ?: error("Unknown status id: $id")
            }
        }
    }

    enum class Gender(val id: Int) {

        Unknown(0),
        Female(1),
        Male(2),
        Genderless(3);

        companion object {

            fun getById(id: Int): Gender {
                return entries.find { it.id == id } ?: error("Unknown gender id: $id")
            }
        }
    }
}
