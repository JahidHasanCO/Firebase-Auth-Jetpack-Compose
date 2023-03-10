package dev.jahidhasanco.firebaseauth.presentation.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.jahidhasanco.firebaseauth.R
import dev.jahidhasanco.firebaseauth.presentation.screen.destinations.HomeScreenDestination
import dev.jahidhasanco.firebaseauth.presentation.screen.destinations.LoginScreenDestination
import dev.jahidhasanco.firebaseauth.presentation.screen.destinations.SignUpScreenDestination
import dev.jahidhasanco.firebaseauth.presentation.screen.destinations.WelcomeScreenDestination
import dev.jahidhasanco.firebaseauth.presentation.viewmodel.AuthViewModel
import dev.jahidhasanco.firebaseauth.ui.theme.Teal200
import dev.jahidhasanco.firebaseauth.ui.theme.primaryColor
import dev.jahidhasanco.firebaseauth.ui.theme.primaryColorAlpha

@SuppressLint("StateFlowValueCalledInComposition")
@Destination(start = true)
@Composable
fun WelcomeScreen(
    navigator: DestinationsNavigator,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    authViewModel.loggedUser()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState(
        initialValue = authViewModel.user.value,
        key1 = lifecycle,
        key2 = authViewModel

    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            authViewModel.user.collect {
                value = it
                it.data?.let { _ ->
                    navigator.navigate(HomeScreenDestination)
                    navigator.clearBackStack(WelcomeScreenDestination)
                }
            }
        }
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Teal200
        )
        systemUiController.setNavigationBarColor(
            color = primaryColor
        )
    }

    if (uiState.isLoading) {
        Column(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                color = Color.White
            )
        }
    } else if (uiState.error.isNotBlank()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            val (image, container) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.girl_pic_1),
                contentDescription = "Girl pic",
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop,

                )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(container) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .background(
                        color = primaryColorAlpha
                    )

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {

                    Text(
                        text = "Welcome", modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 20.dp, end = 20.dp),
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "Welcome to our app. Sign In/Sign Up your account for exploring our services.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp, start = 20.dp, end = 20.dp),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start
                    )


                    Button(
                        onClick = {
                            navigator.navigate(LoginScreenDestination)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 30.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        border = BorderStroke(1.dp, Color.White)

                    ) {
                        Text(
                            text = "Login", modifier = Modifier.padding(5.dp),
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            color = Color.White
                        )
                    }


                    Button(
                        onClick = {
                            navigator.navigate(SignUpScreenDestination)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = "Create Account", modifier = Modifier.padding(5.dp),
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    //  WelcomeScreen()
}