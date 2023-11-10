package com.truemd5.myfilmapp.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.truemd5.myfilmapp.retrofit.ApiTMdB
import com.truemd5.myfilmapp.retrofit.TmdbMovie
import com.truemd5.myfilmapp.scaffold.jaquettes.jaquetteFilm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.forEach

@Composable
fun FilmsView(listeFilms: List<TmdbMovie>,nc : NavHostController) {




    LazyVerticalGrid(columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp))

    {

        items(listeFilms){movie ->

             jaquetteFilm(titreFilm = movie.original_title, lienCover ="https://image.tmdb.org/t/p/original"+movie.poster_path, dateSortie = movie.release_date )

        }



    }

}
