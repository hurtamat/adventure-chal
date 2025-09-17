package hurta.matej.adventure_challenge.feature.date.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hurta.matej.adventure_challenge.feature.date.data.DateRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class SearchViewModel(private val dateRepository: DateRepository) : ViewModel() {

    private val _screenStateStream = MutableStateFlow(SearchScreenState())
    val screenStateStream = _screenStateStream.asStateFlow()

    private var searchJob: Job? = null

    fun searchCharacters(query: String) {
        searchJob?.cancel()
        if (query.isBlank()) {
            clearText()
        } else {
            _screenStateStream.update { it.copy(query = query) }
            search(nameFilter = query)
        }
    }

    fun clearText() {
        _screenStateStream.update {
            it.copy(
                query = "",
                characters = emptyList()
            )
        }
    }

    private fun search(nameFilter: String) {
        searchJob = viewModelScope.launch {
            try {
                delay(200.milliseconds)
                val characters = dateRepository.search(nameFilter = nameFilter)
                _screenStateStream.update { it.copy(characters = characters) }
            } catch (t: Throwable) {
                // Ideally normally handle error in UI
                Log.d("SearchViewModel", t.message, t)
            }
        }
    }
}

data class SearchScreenState(
    val query: String = "",
    val characters: List<Character> = emptyList(),
)
