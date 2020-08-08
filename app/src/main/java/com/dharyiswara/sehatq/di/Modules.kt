package com.dharyiswara.sehatq.di

import com.dharyiswara.sehatq.helper.AppExecutors
import com.dharyiswara.sehatq.network.NetworkFactory
import com.dharyiswara.sehatq.preferences.UserSession
import com.dharyiswara.sehatq.repository.HomepageRepository
import com.dharyiswara.sehatq.ui.main.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {

    single { AppExecutors() }

    single { UserSession(get()) }

}

val networkModule = module {

    single { NetworkFactory.makeNetworkService(get(), get(), get()) }

    single { NetworkFactory.makeClientService(get(), get()) }

    single { NetworkFactory.makeLoggingInterceptor() }

    single { NetworkFactory.makeGson() }

    single { NetworkFactory.makeCache(get()) }
}

val repositoryModule = module {

    single { HomepageRepository(get(), get(), get()) }

}

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }

}

val databaseModule = module {

    single { makeDatabase(get()) }

    single { makeHomepageDao(get()) }

}