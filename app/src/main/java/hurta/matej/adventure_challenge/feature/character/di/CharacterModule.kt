package hurta.matej.adventure_challenge.feature.character.di

import hurta.matej.adventure_challenge.feature.character.presentation.search.SearchViewModel
import hurta.matej.adventure_challenge.core.data.db.RickAndMortyDatabase
import hurta.matej.adventure_challenge.feature.character.data.CharacterRepository
import hurta.matej.adventure_challenge.feature.character.data.db.CharacterLocalDataSource
import hurta.matej.adventure_challenge.feature.character.presentation.list.ListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val characterModule = module {

    single { get<RickAndMortyDatabase>().characterDao() }
    factoryOf(::CharacterLocalDataSource)

    singleOf(::CharacterRepository)

    viewModelOf(::ListViewModel)
    viewModelOf(::SearchViewModel)
}
