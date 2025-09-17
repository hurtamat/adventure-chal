package hurta.matej.adventure_challenge.core.presentation.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import hurta.matej.adventure_challenge.R
import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object List : Screen

    @Serializable
    data class DatesDetail(val id: Int) : Screen

    @Serializable
    data object Start : Screen
}
