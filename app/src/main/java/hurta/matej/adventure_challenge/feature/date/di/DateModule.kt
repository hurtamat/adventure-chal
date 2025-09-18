package hurta.matej.adventure_challenge.feature.date.di

import hurta.matej.adventure_challenge.core.data.db.DateDatabase
import hurta.matej.adventure_challenge.feature.date.data.DateRepository
import hurta.matej.adventure_challenge.feature.date.data.DateSeeder
import hurta.matej.adventure_challenge.feature.date.data.db.DateLocalDataSource
import hurta.matej.adventure_challenge.feature.date.presentation.list.ListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val dateModule = module {

    single { get<DateDatabase>().dateDao() }
    factoryOf(::DateLocalDataSource)

    singleOf(::DateRepository)
    singleOf(::DateSeeder)

    viewModelOf(::ListViewModel)
}
