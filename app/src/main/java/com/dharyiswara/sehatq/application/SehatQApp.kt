package com.dharyiswara.sehatq.application

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.dharyiswara.sehatq.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SehatQApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        startKoin {
            androidContext(this@SehatQApp)
            modules(commonModule)
        }
    }

}