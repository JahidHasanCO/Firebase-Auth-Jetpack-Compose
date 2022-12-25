package dev.jahidhasanco.firebaseauth.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import dev.jahidhasanco.firebaseauth.presentation.screen.NavGraphs
import dev.jahidhasanco.firebaseauth.ui.theme.FirebaseAuthTheme

@AndroidEntryPoint
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


