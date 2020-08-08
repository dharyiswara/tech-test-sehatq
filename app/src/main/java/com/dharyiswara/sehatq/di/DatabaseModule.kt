package com.dharyiswara.sehatq.di

import android.content.Context
import androidx.room.Room
import com.dharyiswara.sehatq.database.SehatQDatabase
import com.dharyiswara.sehatq.database.dao.HomepageDao
import com.dharyiswara.sehatq.helper.extension.clazz

fun makeDatabase(context: Context): SehatQDatabase {
    return Room.databaseBuilder(context, clazz<SehatQDatabase>(), "SehatQ.db")
        .fallbackToDestructiveMigration()
        .build()
}

fun makeHomepageDao(database: SehatQDatabase): HomepageDao {
    return database.homepageDao
}