package com.truemd5.myfilmapp.scaffold.jaquettes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun jaquetteActeur(nomActeur: String, lienCover:String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .shadow(
                elevation = 20.dp,

                )
            .background(color = Color.White),
        contentAlignment = Alignment.Center,

        ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround) {
            AsyncImage(
                model = lienCover,
                contentDescription = "Ma super image"
            )
            Text(text = nomActeur,
                modifier = Modifier.padding(top = 12.dp))

        }


    }
}