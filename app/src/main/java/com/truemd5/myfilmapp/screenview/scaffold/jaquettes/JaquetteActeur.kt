package com.truemd5.myfilmapp.screenview.scaffold.jaquettes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.truemd5.myfilmapp.retrofit.TmdbActor
import com.truemd5.myfilmapp.retrofit.TmdbSerie
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun jaquetteActeur(

    acteurToLook: MutableStateFlow<TmdbActor>,
    acteurDansLaJaquette : TmdbActor,
    nc : NavHostController,
    taille : WindowSizeClass

) {

    when (taille.heightSizeClass) {

        //couché
        WindowHeightSizeClass.Compact->{ Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .shadow(
                    elevation = 20.dp,

                    )
                .background(color = Color.White)
                .clickable { acteurToLook.value=acteurDansLaJaquette
                    nc.navigate("descriptionacteur") },
            contentAlignment = Alignment.Center,

            ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/original"+acteurDansLaJaquette.profile_path,
                    contentDescription = "Photo de l'acteur",
                    modifier = Modifier.height(150.dp)
                )
                Text(text = acteurDansLaJaquette.name,
                    modifier = Modifier.padding(top = 12.dp))

            }


        }}
        //debout
        WindowHeightSizeClass.Medium->{ Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .shadow(
                    elevation = 20.dp,

                    )
                .background(color = Color.White)
                .clickable { acteurToLook.value=acteurDansLaJaquette
                    nc.navigate("descriptionacteur") },
            contentAlignment = Alignment.Center,

            ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/original"+acteurDansLaJaquette.profile_path,
                    contentDescription = "Photo de l'acteur",
                    modifier = Modifier.height(300.dp)
                )
                Text(text = acteurDansLaJaquette.name,
                    modifier = Modifier.padding(top = 12.dp))

            }


        }}
    }

}