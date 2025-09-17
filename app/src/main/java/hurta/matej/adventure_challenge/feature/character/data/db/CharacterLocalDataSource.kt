package hurta.matej.adventure_challenge.feature.character.data.db

import hurta.matej.adventure_challenge.feature.character.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterLocalDataSource(private val characterDao: CharacterDao) {

    fun getAllStream(): Flow<List<Character>> {
        return characterDao.getAllStream().map { dbCharacters ->
            dbCharacters.map { it.toDomain() }
        }
    }

    private fun DbCharacter.toDomain(): Character {
        return Character(
            id = id,
            name = name,
            status = Character.Status.getById(statusId),
            species = species,
            type = type,
            gender = Character.Gender.getById(genderId),
            origin = origin,
            location = location,
            imageUrl = imageUrl,
            favorite = favorite,
        )
    }

    fun getCharacterStream(id: Int): Flow<Character?> {
        return characterDao.getCharacterStream(id).map { it?.toDomain() }
    }

    suspend fun synchronize(characters: List<Character>) {
        characterDao.synchronize(characters.toDb())
    }

    private fun List<Character>.toDb(): List<DbCharacter> {
        return map { it.toDb() }
    }

    private fun Character.toDb(): DbCharacter {
        return DbCharacter(
            id = id,
            name = name,
            statusId = status.id,
            species = species,
            type = type,
            genderId = gender.id,
            origin = origin,
            location = location,
            imageUrl = imageUrl,
            favorite = favorite,
        )
    }

    suspend fun update(character: Character) {
        characterDao.update(character.toDb())
    }
}
