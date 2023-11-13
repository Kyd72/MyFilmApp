package com.truemd5.myfilmapp.screenview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.truemd5.myfilmapp.R


@Composable
    fun StartView(nc : NavHostController) {










        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(
            ) {
                AsyncImage(
                    model="https://img.over-blog-kiwi.com/1/48/26/66/20150220/ob_20887a_cochon.jpg",
                    contentDescription = "Photo de MD5",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)

                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "TrueMD5", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Alternant en e-santé", fontSize = 16.sp, fontStyle = FontStyle.Italic)
                Spacer(modifier = Modifier.height(24.dp))
                mesAdresses()
                Spacer(modifier = Modifier.height(9.dp))
                Button(onClick = { nc.navigate("accueil") }) {
                    Text("Bienvenue, Commencer")
                }            }




        }
    }

@Composable
fun monAdresse1(){


    Row() {
        Icon(
            imageVector = Icons.Default.Mail, // Icône par défaut pour l'e-mail
            contentDescription = "logo mail",
            modifier = Modifier.size(15.dp)
        )
        Text(text = "aleatoire@aleatoire.com", fontSize = 13.sp, modifier = Modifier.align(alignment = Alignment.CenterVertically))

    }
}

@Composable
fun monAdresse2(){


    Row() {
        Image(painterResource(id = R.drawable.logolinkedin) , contentDescription = "logo linkedin", modifier = Modifier.width(15.dp))
        Text(text = "TrueMD5", fontSize = 13.sp,modifier = Modifier.align(alignment = Alignment.CenterVertically))

    }
}

@Composable
fun mesAdresses(){


    Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 30.dp), horizontalAlignment = Alignment.Start) {
        monAdresse1()
        monAdresse2()

    }




}





