package com.truemd5.myfilmapp.screenview.scaffold.descriptions

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.truemd5.myfilmapp.retrofit.Filmographie
import com.truemd5.myfilmapp.retrofit.TmdbActor
import com.truemd5.myfilmapp.screenview.scaffold.jaquettes.jaquetteFilm
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("SuspiciousIndentation")
@Composable
fun DescriptionActeurView(nc : NavHostController, mv: State<TmdbActor>,
                          filmographieToLook: MutableStateFlow<Filmographie>) {


        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            AsyncImage(
                model = "https://image.tmdb.org/t/p/original"+mv.value.profile_path,
                contentDescription = "Photo de l'acteur",
                modifier = Modifier
                    .size(150.dp)

            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(text = mv.value.original_name, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = mv.value.known_for_department, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Filmographie", fontSize = 30.sp, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp))

            {

                items(mv.value.known_for){movie ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .shadow(
                                elevation = 20.dp,

                                )
                            .background(color = Color.White)
                            .clickable { filmographieToLook.value=movie
                                nc.navigate("descriptionmixte") }
                            /*.clickable { movieToLook.value=filmDansLaJaquette
                                nc.navigate("descriptionfilm") }*/,
                        contentAlignment = Alignment.Center,


                        ) {

                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/original"+movie.poster_path,
                                contentDescription = "Ma super image"
                            )
                            Text(text = movie.original_title,
                                modifier = Modifier.padding(top = 12.dp))

                        }


                    }


                }



            }



        }


}