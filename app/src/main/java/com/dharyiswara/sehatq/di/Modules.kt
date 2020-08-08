package com.dharyiswara.sehatq.di

import com.dharyiswara.sehatq.helper.AppExecutors
import com.dharyiswara.sehatq.preferences.UserSession
import org.koin.dsl.module

val commonModule = module {

    single { AppExecutors() }

    single { UserSession(get()) }

}