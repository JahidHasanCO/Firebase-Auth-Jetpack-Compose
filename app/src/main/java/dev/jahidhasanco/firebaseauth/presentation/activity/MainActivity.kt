package dev.jahidhasanco.firebaseauth.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import dev.jahidhasanco.firebaseauth.presentation.screen.LoginScreen
import dev.jahidhasanco.firebaseauth.presentation.screen.NavGraphs
import dev.jahidhasanco.firebaseauth.ui.theme.FirebaseAuthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseAuthTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}


