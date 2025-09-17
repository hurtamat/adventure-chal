package hurta.matej.adventure_challenge.core.presentation.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import hurta.matej.adventure_challenge.R
import kotlinx.serialization.Serializable

sealed interface Screen {

    sealed interface TopLevel : Screen {
        @get:DrawableRes
        val icon: Int

        @get:StringRes
        val title: Int

        @Serializable
        data object Characters : TopLevel {
            override val title: Int
                get() = R.string.characters

            override val icon: Int
                get() = R.drawable.ic_characters
        }

        companion object {
            val all get() = listOf(Characters)
        }
    }

    @Serializable
    data object Search : Screen

    @Serializable
    data class CharacterDetail(val id: Int) : Screen
}
