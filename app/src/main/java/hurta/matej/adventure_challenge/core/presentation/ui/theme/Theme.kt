package hurta.matej.adventure_challenge.core.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun Homework2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorSchema = if (darkTheme) {
        HomeworkDarkColorSchema
    } else {
        HomeworkLightColorSchema
    }

    MaterialTheme(
        colorScheme = colorSchema,
        typography = HomeworkTypography,
        content = content,
    )
}
