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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.truemd5.myfilmapp.retrofit.TmdbMovie
import com.truemd5.myfilmapp.retrofit.TmdbSerie
import com.truemd5.myfilmapp.scaffold.jaquettes.jaquetteFilm
import com.truemd5.myfilmapp.scaffold.jaquettes.jaquetteSerie

@Composable
fun SeriesView(listeSeries: List<TmdbSerie>, nc : NavHostController) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp))

    {

        items(listeSeries){serie ->

            jaquetteSerie(titreFilm = serie.name, lienCover ="https://image.tmdb.org/t/p/original"+serie.poster_path, dateSortie = serie.first_air_date )

        }



    }
}