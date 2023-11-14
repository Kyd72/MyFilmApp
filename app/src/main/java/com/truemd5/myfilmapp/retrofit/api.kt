package com.truemd5.myfilmapp.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiTMdB {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String
                           ,@Query("language") langue : String="fr"
    ): TmdbMovieResult

    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String
                           ,@Query("language") langue : String="fr"
    ): TmdbSerieResult

    @GET("trending/person/week")
    suspend fun lastpersons(@Query("api_key") api_key: String
                            ,@Query("language") langue : String="fr"
    ): TmdbActorResult

    @GET("search/movie")
    suspend fun searchmovies(@Query("api_key") api_key: String,
                             @Query("query") searchtext: String
                             ,@Query("language") langue : String="fr"
    ): TmdbMovieResult

    @GET("search/tv")
    suspend fun searchseries(@Query("api_key") api_key: String,
                             @Query("query") searchtext: String
                             ,@Query("language") langue : String="fr"
    ): TmdbSerieResult

    @GET("search/person")
    suspend fun searchpersons(@Query("api_key") api_key: String,
                             @Query("query") searchtext: String
                              ,@Query("language") langue : String="fr"
    ): TmdbActorResult


    @GET("movie/{id}")
    suspend fun movieDetails(@Path("id") id: String,
                             @Query("api_key") api_key: String,
                             @Query("language") langue : String="fr",
                             @Query("append_to_response") c : String="credits"
    ): MovieDetail

    @GET("tv/{id}?")
    suspend fun serieDetails(@Path("id") id: String
                             ,@Query("language") langue : String="fr"
    ): TmdbSerie
}


