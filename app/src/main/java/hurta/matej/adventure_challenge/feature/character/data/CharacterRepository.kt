package hurta.matej.adventure_challenge.feature.character.data

import hurta.matej.adventure_challenge.feature.character.data.db.CharacterLocalDataSource
import hurta.matej.adventure_challenge.feature.character.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CharacterRepository(
    private val characterLocalDataSource: CharacterLocalDataSource,
) {

    fun getAllStream(): Flow<List<Character>> = characterLocalDataSource.getAllStream()

    fun getFavoritesStream(): Flow<List<Character>> {
        return getAllStream().map { characters ->
            characters.filter { it.favorite }
        }
    }

    fun getCharacterStream(id: Int): Flow<Character?> = characterLocalDataSource.getCharacterStream(id)


    suspend fun update(character: Character) {
        characterLocalDataSource.update(character)
    }

    suspend fun search(nameFilter: String): List<Character> {
        return characterLocalDataSource.getAllStream().map { characters ->
            characters.filter { character ->
                character.name.contains(nameFilter, ignoreCase = true)
            }
        }.first()
    }
}
