package com.truemd5.myfilmapp.screenview.scaffold.descriptions

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.truemd5.myfilmapp.configurations.ConfigViewModel
import com.truemd5.myfilmapp.retrofit.TmdbActor
import com.truemd5.myfilmapp.retrofit.TmdbMovie
import com.truemd5.myfilmapp.retrofit.TmdbSerie
import com.truemd5.myfilmapp.retrofit.toTmdbActor
import com.truemd5.myfilmapp.screenview.scaffold.jaquettes.jaquetteActeur
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DescriptionSerieView(nc : NavHostController, vm: ConfigViewModel, mv: State<TmdbSerie>,
                         acteurToLook : MutableStateFlow<TmdbActor>, taille : WindowSizeClass)  {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()
    val coroutineScope2 = rememberCoroutineScope()

    val pagerStateD = rememberPagerState(pageCount = { vm.seriedetails.value.credits.cast.size })


    val pages = listOf(
        "https://image.tmdb.org/t/p/original"+mv.value.poster_path, // URL de la première image
        "https://image.tmdb.org/t/p/original"+mv.value.backdrop_path// URL de la deuxième image
    )
    val scrollState = rememberScrollState()
    vm.searchSerieDetails(id=mv.value.id.toString(),)



    Column(
        modifier = Modifier.padding(16.dp).
                     verticalScroll(scrollState)
    ) {


        //DEBUT
        // ViewPager pour les images de couverture
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
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
            text = mv.value.name,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Date de sortie
        Text(
            text = "Date de 1ere diffusion : ${mv.value.first_air_date}",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Synopsis

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = mv.value.overview,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Text(
            text = "Principaux acteurs",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp)
        )

        HorizontalPager(
            state = pagerStateD,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {act ->
            // Première image de couverture

            jaquetteActeur(
                acteurToLook= acteurToLook,
                acteurDansLaJaquette= vm.seriedetails.collectAsState().value.credits.cast[act].toTmdbActor(),
                nc = nc,
                taille = taille
            )


        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(

                onClick = {
                    coroutineScope.launch {
                        pagerStateD.animateScrollToPage(pagerStateD.currentPage - 1)}

                          }

                ,
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp),
                enabled = pagerStateD.currentPage > 0
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Previous",
                    tint = if (pagerStateD.currentPage > 0) MaterialTheme.colorScheme
                        .onBackground.copy(alpha = 0.6f) else MaterialTheme.colorScheme
                        .onBackground.copy(alpha = 0.2f)
                )
            }

            IconButton(
                onClick = {
                    coroutineScope.launch {pagerStateD.animateScrollToPage(pagerStateD.currentPage + 1)} },
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp),
                enabled = pagerStateD.currentPage < pagerStateD.pageCount - 1
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next",
                    tint = if (pagerStateD.currentPage < pagerStateD.pageCount - 1) MaterialTheme.colorScheme
                        .onBackground.copy(alpha = 0.6f) else MaterialTheme.
                    colorScheme.onBackground.copy(alpha = 0.2f)
                )
            }
        }

    }





}