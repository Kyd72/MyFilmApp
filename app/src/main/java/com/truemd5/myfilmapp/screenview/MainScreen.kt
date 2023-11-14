package com.truemd5.myfilmapp.screenview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.twotone.DesktopWindows
import androidx.compose.material.icons.twotone.Movie
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.truemd5.myfilmapp.configurations.ConfigViewModel
import com.truemd5.myfilmapp.retrofit.Filmographie
import com.truemd5.myfilmapp.retrofit.TmdbActor
import com.truemd5.myfilmapp.retrofit.TmdbMovie
import com.truemd5.myfilmapp.retrofit.TmdbSerie
import com.truemd5.myfilmapp.screenview.scaffold.presentation.ActeursView
import com.truemd5.myfilmapp.screenview.scaffold.presentation.FilmsView
import com.truemd5.myfilmapp.screenview.scaffold.presentation.SeriesView
import com.truemd5.myfilmapp.screenview.scaffold.descriptions.DescriptionActeurView
import com.truemd5.myfilmapp.screenview.scaffold.descriptions.DescriptionFilmView
import com.truemd5.myfilmapp.screenview.scaffold.descriptions.DescriptionMixteView
import com.truemd5.myfilmapp.screenview.scaffold.descriptions.DescriptionSerieView
import kotlinx.coroutines.flow.MutableStateFlow


/**
 * films : La liste des films à afficher ; prendre un State<> pour gérer l'affichage dynamique
 * series : La liste des series à afficher ; prendre un State<> pour gérer l'affichage dynamique
 * acteurs : La liste des acteurs à afficher ; prendre un State<> pour gérer l'affichage dynamique
 * nc : C'est un navHostController pour gérer le routage au sein du scaffold. Le navHost du Scaffold peut être différent du navHost principal
 *      car le Scaffold a ses propres écrans d'affichage (J'utilise 2 navHost pour le projets)
 * vm : ViewModel ; pour pouvoir accéder à la logique métier au sein du Scaffold (MainScreen)
 *
 *
 *
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(nc: NavHostController, vm: ConfigViewModel) {

    var searchText by remember { mutableStateOf("") }
    var searchOpened by remember { mutableStateOf(false) }
    var retour by remember { mutableStateOf(false) }


    val movieToLook = MutableStateFlow<TmdbMovie>(TmdbMovie())

    val serieToLook = MutableStateFlow<TmdbSerie>(TmdbSerie())

    val actorToLook = MutableStateFlow<TmdbActor>(TmdbActor())

    val filmographieToLook = MutableStateFlow<Filmographie>(Filmographie())

    Scaffold(
        topBar = { ->
            //Barre supérieure
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = Color.Black,
                ),

                title= { Text(text="Fav'App by TrueMD5",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500)
                )
                },

                actions = {
                    if(searchOpened){
                        //Debut
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { searchText = it },

                            label = {
                                if(nc.currentBackStackEntry?.destination?.route=="films"){ Text("Rechercher un film")
                                }
                                else if (nc.currentBackStackEntry?.destination?.route=="series"){ Text("Rechercher une série")
                                }
                                else if (nc.currentBackStackEntry?.destination?.route=="acteurs"){ Text("Rechercher un acteur")
                                }
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Search // Action associée à la touche "Entrée"
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    // Action à exécuter lorsque la touche "Entrée" est pressée
                                    if(nc.currentBackStackEntry?.destination?.route=="films"){vm.searchFilm(searchText = searchText)
                                        searchText=""}
                                    else if (nc.currentBackStackEntry?.destination?.route=="series"){vm.searchSerie(searchText = searchText)
                                        searchText=""}
                                    else if (nc.currentBackStackEntry?.destination?.route=="acteurs"){vm.searchActor(searchText = searchText)
                                        searchText=""}

                                    searchOpened = false // Fermer la barre de recherche après la recherche
                                }
                            ),
                            maxLines = 1,
                            trailingIcon = {
                                IconButton(onClick = { searchOpened = false }) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "Fermer"
                                    )
                                }
                            }
                        )
                        //Fin
                    }

                    else{

                        if(retour)

                        {
                            IconButton(
                                onClick = { nc.popBackStack()  },
                                content=   {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Retour"
                                    )
                                })
                        }

                        else {IconButton(
                            onClick = { searchOpened=true },
                            content=   {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Rechercher"
                                )
                            })}
                    }

                }

                /*Les autres paramètres*/

            )

        },

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,

                content= {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        content = {


                            //Bouton Films

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                content={
                                    IconButton(
                                        onClick = { nc.navigate("films")
                                            vm.getLastMovies()

                                        },

                                        content=   {
                                            Icon(
                                                imageVector = Icons.TwoTone.Movie,
                                                contentDescription = "Localized description"
                                            )
                                        })

                                    Text(text ="Films",
                                        modifier = Modifier.padding(bottom = 12.dp))
                                }
                            )

                            //Bouton Series

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                content={
                                    IconButton(
                                        onClick = { nc.navigate("series")
                                            vm.getLastSeries()
                                                  },

                                        content=   {
                                            Icon(
                                                imageVector = Icons.TwoTone.DesktopWindows,
                                                contentDescription = "Localized description"
                                            )
                                        })

                                    Text(text ="Séries",
                                        modifier = Modifier.padding(bottom = 12.dp))
                                }
                            )



                            //Bouton Acteurs

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                content={
                                    IconButton(
                                        onClick = { nc.navigate("acteurs")
                                            vm.getLastActors()
                                                  },

                                        content=   {
                                            Icon(
                                                imageVector = Icons.TwoTone.Person,
                                                contentDescription = "Localized description"
                                            )
                                        })

                                    Text(text ="Acteurs",
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                                }
                            )

                        }
                    )




                })
        },


        content = {it ->
            // Contenu du Scaffold
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it) ,
                verticalArrangement = Arrangement.Center
            ) {

                NavHost(navController = nc, startDestination = "films") {
                    composable("films") {
                        retour=false
                        FilmsView(nc = nc, listeFilms = vm.movies.collectAsState(), movieToLook=movieToLook)
                        // Contenu de la première destination
                    }
                    composable("series") {
                        retour=false
                        // Contenu de la deuxième destination
                        SeriesView(nc = nc, listeSeries = vm.series.collectAsState(), serieToLook = serieToLook )
                    }
                    composable("acteurs") {
                        retour=false
                        // Contenu de la deuxième destination
                        ActeursView(nc = nc, listeActeurs = vm.actors.collectAsState(), acteurToLook = actorToLook )
                    }
                    composable("descriptionfilm") {
                            retour=true
                            DescriptionFilmView(nc =nc,movieToLook.collectAsState(),vm=vm, acteurToLook = actorToLook)
                    }
                    composable("descriptionserie") {
                            retour=true
                             DescriptionSerieView(nc =nc,serieToLook.collectAsState())

                    }
                    composable("descriptionacteur") {
                            retour=true
                            DescriptionActeurView(nc =nc,actorToLook.collectAsState(),filmographieToLook)
                    }

                    composable("descriptionmixte") {
                        retour=true
                        DescriptionMixteView(nc =nc,filmographieToLook.collectAsState())
                    }
                }
            }
        },
        //modifier = Modifier.systemBarsPadding()
    )
}
