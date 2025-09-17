package hurta.matej.adventure_challenge.feature.date.di

import hurta.matej.adventure_challenge.feature.date.presentation.search.SearchViewModel
import hurta.matej.adventure_challenge.core.data.db.RickAndMortyDatabase
import hurta.matej.adventure_challenge.feature.date.data.DateRepository
import hurta.matej.adventure_challenge.feature.date.data.db.DateLocalDataSource
import hurta.matej.adventure_challenge.feature.date.presentation.list.ListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val characterModule = module {

    single { get<RickAndMortyDatabase>().characterDao() }
    factoryOf(::DateLocalDataSource)

    singleOf(::DateRepository)

    viewModelOf(::ListViewModel)
    viewModelOf(::SearchViewModel)
}
