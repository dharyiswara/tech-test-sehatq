package com.dharyiswara.sehatq.application

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.dharyiswara.sehatq.di.*
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SehatQApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        startKoin {
            androidContext(this@SehatQApp)
            modules(
                commonModule,
                networkModule,
                repositoryModule,
                viewModelModule,
                databaseModule
            )
        }

        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }

}