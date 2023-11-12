package com.truemd5.myfilmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.truemd5.myfilmapp.configurations.ConfigViewModel
import com.truemd5.myfilmapp.retrofit.TmdbActor
import com.truemd5.myfilmapp.retrofit.TmdbMovie
import com.truemd5.myfilmapp.retrofit.TmdbSerie
import com.truemd5.myfilmapp.screenview.scaffold.ActeursView
import com.truemd5.myfilmapp.screenview.scaffold.FilmsView
import com.truemd5.myfilmapp.screenview.scaffold.SeriesView
import com.truemd5.myfilmapp.screenview.MainScreen
import com.truemd5.myfilmapp.screenview.StartView


class MainActivity : ComponentActivity() {

    private val viewModel:ConfigViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {



            val navControllerMere = rememberNavController()
            val navControllerScaffold = rememberNavController()


            NavHost(navController = navControllerMere, startDestination = "commencer") {
                composable("commencer") {

                    StartView(navControllerMere)


                }
                composable("accueil") {
                    viewModel.getLastMovies()
                    viewModel.getLastActors()
                    viewModel.getLastSeries()
                    MainScreen(
                        navControllerScaffold,
                        viewModel)
                }

            }





            }

        }


    }














