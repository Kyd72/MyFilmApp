package com.truemd5.myfilmapp.configurations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truemd5.myfilmapp.retrofit.ApiTMdB
import com.truemd5.myfilmapp.retrofit.TmdbMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ConfigViewModel: ViewModel() {

    val retrof=  Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val api = retrof.create(ApiTMdB::class.java)

    val api_key = "6a843a2b0fafd93ef194611c724fa115"

    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())



    fun getMovies() {
        viewModelScope.launch {
            movies.value = api.lastmovies(api_key).results
        }
    }


}

