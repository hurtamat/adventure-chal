package hurta.matej.adventure_challenge.feature.character.presentation.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hurta.matej.adventure_challenge.feature.character.data.CharacterRepository
import hurta.matej.adventure_challenge.feature.character.domain.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    private val _screenStateStream = MutableStateFlow(ListScreenState())
    val screenStateStream = _screenStateStream.asStateFlow()

    init {
        characterRepository.getAllStream()
            .onEach { characters ->
                _screenStateStream.update {
                    it.copy(characters = characters)
                }
            }
            .launchIn(viewModelScope)

    }
}

data class ListScreenState(
    val characters: List<Character> = emptyList(),
)
