package hurta.matej.adventure_challenge.feature.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import hurta.matej.adventure_challenge.core.presentation.ui.theme.Homework2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework2Theme {
                MainScreen()
            }
        }
    }
}
