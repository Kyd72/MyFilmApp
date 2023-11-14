package com.truemd5.myfilmapp.screenview.scaffold.descriptions

import android.annotation.SuppressLint
import android.graphics.Movie
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.truemd5.myfilmapp.configurations.ConfigViewModel
import com.truemd5.myfilmapp.retrofit.Filmographie
import com.truemd5.myfilmapp.retrofit.TmdbActor
import com.truemd5.myfilmapp.retrofit.TmdbMovie
import com.truemd5.myfilmapp.retrofit.toTmdbActor
import com.truemd5.myfilmapp.screenview.scaffold.jaquettes.jaquetteActeur
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun DescriptionFilmView(nc : NavHostController, mv:  State<TmdbMovie>, vm: ConfigViewModel,
                            acteurToLook : MutableStateFlow<TmdbActor>) {
        val pagerState = rememberPagerState(pageCount = { 2 })
    val pagerStateD = rememberPagerState(pageCount = { vm.moviedetails.value.credits.cast.size })

    val coroutineScope = rememberCoroutineScope()

        vm.searchMovieDetails(id=mv.value.id,)

    val pages = listOf(
        "https://image.tmdb.org/t/p/original"+mv.value.poster_path, // URL de la première image
        "https://image.tmdb.org/t/p/original"+mv.value.backdrop_path// URL de la deuxième image
    )
    val scrollState: ScrollState = rememberScrollState()






        Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())


            ) {

                // ViewPager pour les images de couverture
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {pgs ->
                    // Première image de couverture


                        AsyncImage(
                            model = pages[pgs],
                            contentDescription = "Ma super image"
                        )


                }

                // Indicateurs de défilement (flèches)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(

                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)} }

                        ,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp),
                        enabled = pagerState.currentPage > 0
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous",
                            tint = if (pagerState.currentPage > 0) MaterialTheme.colorScheme
                                .onBackground.copy(alpha = 0.6f) else MaterialTheme.colorScheme
                                .onBackground.copy(alpha = 0.2f)
                        )
                    }

                    IconButton(
                        onClick = {
                            coroutineScope.launch {pagerState.animateScrollToPage(pagerState.currentPage + 1)} },
                        modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp),
                        enabled = pagerState.currentPage < pagerState.pageCount - 1
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Next",
                            tint = if (pagerState.currentPage < pagerState.pageCount - 1) MaterialTheme.colorScheme
                                .onBackground.copy(alpha = 0.6f) else MaterialTheme.
                            colorScheme.onBackground.copy(alpha = 0.2f)
                        )
                    }
                }

                // Titre, date de sortie et synopsis...

                Spacer(modifier = Modifier.height(16.dp))

                // Titre
                Text(
                    text = mv.value.original_title,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Date de sortie
                Text(
                    text = "Date de sortie : ${mv.value.release_date}",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Synopsis



                Text(
                    text = mv.value.overview,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Principaux acteurs",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp)
                )


              /*  LazyVerticalGrid(columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(1.dp))

                {

                    items(vm.moviedetails.value.credits.cast){actor ->

                        jaquetteActeur(
                            acteurToLook= acteurToLook,
                            acteurDansLaJaquette= actor.toTmdbActor(),
                            nc = nc
                        )





                    }
                }*/

            HorizontalPager(
                state = pagerStateD,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {act ->
                // Première image de couverture

                jaquetteActeur(
                    acteurToLook= acteurToLook,
                    acteurDansLaJaquette= vm.moviedetails.collectAsState().value.credits.cast[act].toTmdbActor(),
                    nc = nc
                )


            }


            }









    //*****************************************************

/*
    Column(modifier = Modifier.padding(16.dp)) {
        // Section des images de couverture et défilement
        Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth().height(400.dp)
            ) {pgs ->
                // Première image de couverture

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AsyncImage(
                        model = pages[pgs],
                        contentDescription = "Ma super image"
                    )
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(

                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)} }

                    ,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp),
                    enabled = pagerState.currentPage > 0
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Previous",
                        tint = if (pagerState.currentPage > 0) MaterialTheme.colorScheme
                            .onBackground.copy(alpha = 0.6f) else MaterialTheme.colorScheme
                            .onBackground.copy(alpha = 0.2f)
                    )
                }

                IconButton(
                    onClick = {
                        coroutineScope.launch {pagerState.animateScrollToPage(pagerState.currentPage + 1)} },
                    modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp),
                    enabled = pagerState.currentPage < pagerState.pageCount - 1
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Next",
                        tint = if (pagerState.currentPage < pagerState.pageCount - 1) MaterialTheme.colorScheme
                            .onBackground.copy(alpha = 0.6f) else MaterialTheme.
                        colorScheme.onBackground.copy(alpha = 0.2f)
                    )
                }
            }
            // Flèches de navigation
            // ... L'ensemble des flèches (Row avec IconButton)
        }

        // Section des détails du film
        Spacer(modifier = Modifier.height(16.dp))

        // Titre
        Text(
            text = mv.value.original_title,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Date de sortie
        Text(
            text = "Date de sortie : ${mv.value.release_date}",
            style = MaterialTheme.typography.bodySmall
        )
        // ... Titre, date de sortie, synopsis, etc.
        Text(
            text = mv.value.overview,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Principaux acteurs",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))


        // Section des acteurs
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(vm.moviedetails.value.credits.cast) { actor ->
                jaquetteActeur(
                    acteurToLook = acteurToLook,
                    acteurDansLaJaquette = actor.toTmdbActor(),
                    nc = nc
                )
            }
        }
    }
*/







}
