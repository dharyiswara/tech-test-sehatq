package com.dharyiswara.sehatq.repository

import androidx.lifecycle.LiveData
import com.dharyiswara.sehatq.database.dao.HomepageDao
import com.dharyiswara.sehatq.helper.ApiResponse
import com.dharyiswara.sehatq.helper.AppExecutors
import com.dharyiswara.sehatq.helper.Resource
import com.dharyiswara.sehatq.helper.base.BaseRepository
import com.dharyiswara.sehatq.model.HomepageData
import com.dharyiswara.sehatq.network.NetworkService

class HomepageRepository(
    private val service: NetworkService,
    private val database: HomepageDao,
    private val appExecutors: AppExecutors
) {

    fun getHomepage(isReloadData: Boolean): LiveData<Resource<List<HomepageData>>> {
        return object : BaseRepository<List<HomepageData>>(appExecutors) {
            override fun saveFromNetwork(item: List<HomepageData>) {
                database.saveHomepage(item)
            }

            override fun shouldFetchFromNetwork(data: List<HomepageData>?): Boolean {
                if (isReloadData) return true
                return data == null || data.isEmpty()
            }

            override fun loadFromLocal(): LiveData<List<HomepageData>> {
                return database.getHomepage()
            }

            override fun loadFromNetwork(): LiveData<ApiResponse<List<HomepageData>>> {
                return service.getHomePage()
            }

        }.asLiveData()
    }

}