package dev.jahidhasanco.firebaseauth.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Home Screen")
    }

}