package com.truemd5.myfilmapp.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.truemd5.myfilmapp.retrofit.TmdbActor
import com.truemd5.myfilmapp.scaffold.jaquettes.jaquetteActeur

@Composable
fun ActeursView(nc: NavHostController, listeActeurs: State<List<TmdbActor>>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp))

    {

        items(listeActeurs.value){actor ->

            jaquetteActeur(nomActeur = actor.name, lienCover ="https://image.tmdb.org/t/p/original"+actor.profile_path )

        }



    }
}