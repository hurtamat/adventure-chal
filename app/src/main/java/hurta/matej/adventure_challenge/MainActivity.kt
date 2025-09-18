package hurta.matej.adventure_challenge

import android.app.Application
import hurta.matej.adventure_challenge.core.di.coreModule
import hurta.matej.adventure_challenge.feature.date.data.DateSeeder
import hurta.matej.adventure_challenge.feature.date.di.dateModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class App : Application(), KoinComponent {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val dateSeeder: DateSeeder by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(coreModule, dateModule)
        }

        applicationScope.launch {
            dateSeeder.seedDatabase()
        }
    }
}