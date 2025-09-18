package hurta.matej.adventure_challenge.feature.date.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import hurta.matej.adventure_challenge.core.presentation.ui.Screen
import hurta.matej.adventure_challenge.feature.date.data.DateRepository
import hurta.matej.adventure_challenge.feature.date.domain.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val dateRepository: DateRepository,
) : ViewModel() {

    private val id: Int
        get() = savedStateHandle.toRoute<Screen.DatesDetail>().id

    private val _screenStateStream = MutableStateFlow(DetailScreenState())
    val screenStateStream = _screenStateStream.asStateFlow()

    init {
        dateRepository.getDateStream(id = id)
            .onEach { date ->
                _screenStateStream.update { it.copy(date = date) }
            }
            .launchIn(viewModelScope)
    }

    fun onFavoriteClick() {
        screenStateStream.value.date?.let { date ->
            val updatedDate = date.copy(photoPresent = !date.photoPresent)
            viewModelScope.launch {
                dateRepository.updateDate(date = updatedDate)
            }
        }
    }
}

data class DetailScreenState(
    val date: Date? = null,
)
