package com.dharyiswara.sehatq.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dharyiswara.sehatq.helper.Resource
import com.dharyiswara.sehatq.model.HomepageData
import com.dharyiswara.sehatq.repository.HomepageRepository

class HomeViewModel(private val repository: HomepageRepository) : ViewModel() {

    private val homepageRequest = MutableLiveData<Boolean>()

    val homepageData: LiveData<Resource<List<HomepageData>>> = Transformations
        .switchMap(homepageRequest) {
            repository.getHomepage(it)
        }

    fun getHomepage(isReloadData: Boolean) {
        homepageRequest.value = isReloadData
    }

}