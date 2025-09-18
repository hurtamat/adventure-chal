package hurta.matej.adventure_challenge

import android.app.Application
import hurta.matej.adventure_challenge.core.di.coreModule
import hurta.matej.adventure_challenge.feature.date.di.dateModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(coreModule, dateModule)
        }
    }
}