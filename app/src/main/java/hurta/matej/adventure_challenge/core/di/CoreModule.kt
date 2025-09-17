package hurta.matej.adventure_challenge.core.di

import hurta.matej.adventure_challenge.core.data.db.DateDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreModule = module {
    single { DateDatabase.newInstance(androidContext()) }
}
