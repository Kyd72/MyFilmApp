package com.truemd5.myfilmapp.configurations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truemd5.myfilmapp.retrofit.ApiTMdB
import com.truemd5.myfilmapp.retrofit.TmdbActor
import com.truemd5.myfilmapp.retrofit.TmdbMovie
import com.truemd5.myfilmapp.retrofit.TmdbSerie
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

    val series = MutableStateFlow<List<TmdbSerie>>(listOf())

    val actors = MutableStateFlow<List<TmdbActor>>(listOf())



    fun getLastMovies() {
        viewModelScope.launch {
            movies.value = api.lastmovies(api_key).results
        }
    }

    fun getLastSeries() {
        viewModelScope.launch {
            series.value = api.lastseries(api_key).results
        }
    }

    fun getLastActors() {
        viewModelScope.launch {
            actors.value = api.lastpersons(api_key).results
        }
    }

    fun searchFilm(searchText : String) {
        viewModelScope.launch {
            movies.value = api.searchmovies(api_key, searchText).results

        }
    }
    fun searchSerie(searchText : String) {
        viewModelScope.launch {
            series.value = api.searchseries(api_key, searchText).results
        }
    }
    fun searchActor(searchText : String) {
        viewModelScope.launch {
            actors.value = api.searchpersons(api_key, searchText).results
        }
    }


}

