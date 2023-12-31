package com.truemd5.myfilmapp.configurations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truemd5.myfilmapp.retrofit.ApiTMdB
import com.truemd5.myfilmapp.retrofit.Filmographie
import com.truemd5.myfilmapp.retrofit.MovieDetail
import com.truemd5.myfilmapp.retrofit.SerieDetail
import com.truemd5.myfilmapp.retrofit.TmdbActor
import com.truemd5.myfilmapp.retrofit.TmdbMovie
import com.truemd5.myfilmapp.retrofit.TmdbSerie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ConfigViewModel: ViewModel() {


    val movieToLook = MutableStateFlow<TmdbMovie>(TmdbMovie())

    val serieToLook = MutableStateFlow<TmdbSerie>(TmdbSerie())

    val actorToLook = MutableStateFlow<TmdbActor>(TmdbActor())

    val filmographieToLook = MutableStateFlow<Filmographie>(Filmographie())



    val retrof=  Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val api = retrof.create(ApiTMdB::class.java)

    val api_key = "6a843a2b0fafd93ef194611c724fa115"

    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())

    val series = MutableStateFlow<List<TmdbSerie>>(listOf())

    val actors = MutableStateFlow<List<TmdbActor>>(listOf())

    val moviedetails = MutableStateFlow<MovieDetail>(MovieDetail())

    val seriedetails = MutableStateFlow<SerieDetail>(SerieDetail())







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

    fun searchMovieDetails(id : String) {
        viewModelScope.launch {
            moviedetails.value = api.movieDetails(id=id,api_key)
        }
    }

    fun searchSerieDetails(id : String) {
        viewModelScope.launch {
            seriedetails.value = api.serieDetails(id=id,api_key)
        }
    }



}

