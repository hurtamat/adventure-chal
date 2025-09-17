package hurta.matej.adventure_challenge.feature.character.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hurta.matej.adventure_challenge.R
import hurta.matej.adventure_challenge.feature.character.domain.Character

@Composable
fun mapText(status: Character.Status): String {
    return when (status) {
        Character.Status.Dead -> R.string.dead
        Character.Status.Alive -> R.string.alive
        Character.Status.Unknown -> R.string.unknown
    }.let { stringResource(id = it) }
}

@Composable
fun mapText(gender: Character.Gender): String {
    return when (gender) {
        Character.Gender.Female -> R.string.female
        Character.Gender.Male -> R.string.male
        Character.Gender.Genderless -> R.string.genderless
        Character.Gender.Unknown -> R.string.unknown
    }.let { stringResource(id = it) }
}
