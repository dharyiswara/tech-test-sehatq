package com.dharyiswara.sehatq.network

import androidx.lifecycle.LiveData
import com.dharyiswara.sehatq.helper.ApiResponse
import com.dharyiswara.sehatq.model.HomepageData
import retrofit2.http.GET

interface NetworkService {

    @GET("/home")
    fun getHomePage(): LiveData<ApiResponse<List<HomepageData>>>

}