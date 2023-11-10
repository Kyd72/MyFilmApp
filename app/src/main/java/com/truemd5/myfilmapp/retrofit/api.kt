package com.truemd5.myfilmapp.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiTMdB {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String): TmdbMovieResult

    @GET("search/movie")
    suspend fun searchmovies(@Query("api_key") api_key: String,
                             @Query("query") searchtext: String): TmdbMovieResult


    @GET("movie/{id}")
    suspend fun movieDetails(@Path("id") id: String): TmdbMovie
}