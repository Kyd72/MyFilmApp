package com.truemd5.myfilmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.twotone.DesktopWindows
import androidx.compose.material.icons.twotone.Movie
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        MainScreen()




            }
           /* MyFilmAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }*/
        }
    }


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    Scaffold(
        topBar = { ->
            //Barre supérieure
         TopAppBar(
             colors = topAppBarColors(
                 containerColor = MaterialTheme.colorScheme.primaryContainer,
                 titleContentColor = Color.Black,
             ),

             title= { Text(text="Fav'App by TrueMD5",
                            fontSize = 18.sp,
                            fontWeight = FontWeight(500)
                 )},

             actions = {
                 IconButton(
                     onClick = { /* do something */ },

              content=   {
                     Icon(
                         imageVector = Icons.Filled.Search,
                         contentDescription = "Localized description"
                     )
                 })
             },


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
                                        onClick = { /* do something */ },

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
                                        onClick = { /* do something */ },

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
                                        onClick = { /* do something */ },

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

            }
        }

    )


}














