package dev.jahidhasanco.firebaseauth.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.jahidhasanco.firebaseauth.common.utils.displayToast
import dev.jahidhasanco.firebaseauth.presentation.screen.destinations.HomeScreenDestination
import dev.jahidhasanco.firebaseauth.presentation.screen.destinations.LoginScreenDestination
import dev.jahidhasanco.firebaseauth.presentation.viewmodel.AuthViewModel
import dev.jahidhasanco.firebaseauth.presentation.viewmodel.LoggedInViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    authViewModel: AuthViewModel = hiltViewModel(),
    loggedInViewModel: LoggedInViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    authViewModel.getUserData()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState(
        initialValue = authViewModel.userData.value,
        key1 = lifecycle,
        key2 = authViewModel

    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            authViewModel.userData.collect {
                value = it
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Home Screen") },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                elevation = 0.dp
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            if (uiState.data != null) {
                Text(
                    text = "Welcome ${authViewModel.userData.value.data?.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    style = MaterialTheme.typography.h5
                )
            } else {
                Text(
                    text = "Welcome Back, Mr/Mrs", modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    style = MaterialTheme.typography.h5
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    loggedInViewModel.logOut()
                    navigator.navigate(LoginScreenDestination)
                    navigator.clearBackStack(HomeScreenDestination)
                    context.displayToast("Logout Successfully")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 30.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Logout", modifier = Modifier.padding(5.dp),
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    color = Color.Black
                )
            }

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ShowHomeScreenPreview() {
//    HomeScreen()
//}