package com.nikechallenge.applemusic.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikechallenge.applemusic.ext.launch
import com.nikechallenge.applemusic.model.MostPlayed
import com.nikechallenge.applemusic.repository.MusicDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicViewModel(application: Application) : AndroidViewModel(application) {
    //Could inject using a data binding library if needed.
    private val repository by lazy { MusicDataRepository.get() }
    private val localDispatchers by lazy { Dispatchers.IO } //can inject test dispatchers

    private val _mostPlayed = MutableLiveData<Result<List<MostPlayed>>>()
    val mostPlayed: LiveData<Result<List<MostPlayed>>>
        get() = _mostPlayed

    fun getMostPlayed(limit: Int) = viewModelScope.launch(_mostPlayed) {
        fetchData(limit)
    }

    private suspend fun fetchData(limit: Int) = withContext(localDispatchers) {
        repository.getMostPlayed(limit)
    }
}