package com.truemd5.myfilmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.truemd5.myfilmapp.configurations.ConfigViewModel
import com.truemd5.myfilmapp.screenview.MainScreen
import com.truemd5.myfilmapp.screenview.StartView



class

MainActivity : ComponentActivity() {


    private val viewModel:ConfigViewModel by viewModels()





    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            /**navControllerScaffold : définir le routage au sein du scaffold
             * navControllerMere : definir le routage entre le scaffold et la présentation du profil
             *
             * */

            val tailleEcran= calculateWindowSizeClass(this)
            val navControllerMere = rememberNavController()
            val navControllerScaffold = rememberNavController()


            NavHost(navController = navControllerMere, startDestination = "commencer") {
                composable("commencer") {

                    StartView(navControllerMere, tailleEcran)


                }
                composable("accueil") {

                    viewModel.getLastMovies()
                    viewModel.getLastActors()
                    viewModel.getLastSeries()

                    MainScreen(
                        navControllerScaffold,
                        viewModel,
                        tailleEcran)
                }

            }
            }

        }


    }
