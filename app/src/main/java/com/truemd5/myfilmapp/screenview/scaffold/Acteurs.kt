package com.truemd5.myfilmapp.screenview.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.truemd5.myfilmapp.retrofit.TmdbActor
import com.truemd5.myfilmapp.retrofit.TmdbSerie
import com.truemd5.myfilmapp.screenview.scaffold.jaquettes.jaquetteActeur
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ActeursView(

    listeActeurs: State<List<TmdbActor>>,
    nc: NavHostController,
    acteurToLook: MutableStateFlow<TmdbActor>

) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp))

    {

        items(listeActeurs.value){actor ->

            jaquetteActeur(
                acteurToLook= acteurToLook,
                acteurDansLaJaquette= actor,
                nc = nc
            )

        }



    }
}