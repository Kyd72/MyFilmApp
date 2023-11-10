package com.truemd5.myfilmapp.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.truemd5.myfilmapp.retrofit.TmdbMovie
import com.truemd5.myfilmapp.scaffold.jaquettes.jaquetteFilm

@Composable
fun FilmsView(listeFilms: State<List<TmdbMovie>>, nc: NavHostController) {




    LazyVerticalGrid(columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp))

    {

        items(listeFilms.value){movie ->

             jaquetteFilm(titreFilm = movie.original_title, lienCover ="https://image.tmdb.org/t/p/original"+movie.poster_path, dateSortie = movie.release_date )

        }



    }

}
