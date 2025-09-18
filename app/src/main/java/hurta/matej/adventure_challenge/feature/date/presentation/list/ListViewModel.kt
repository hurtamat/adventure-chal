package hurta.matej.adventure_challenge.feature.date.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hurta.matej.adventure_challenge.feature.date.data.DateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ListViewModel(private val dateRepository: DateRepository) : ViewModel() {

    private val _screenStateStream = MutableStateFlow(ListScreenState())
    val screenStateStream = _screenStateStream.asStateFlow()

    init {
        dateRepository.getAllDatesStream()
            .onEach { dates ->
                _screenStateStream.update {
                    it.copy(dates = dates)
                }
            }
            .launchIn(viewModelScope)

    }
}

data class ListScreenState(
    val dates: List<hurta.matej.adventure_challenge.feature.date.domain.Date> = emptyList(),
)
