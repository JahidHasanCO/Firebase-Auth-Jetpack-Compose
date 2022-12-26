package dev.jahidhasanco.firebaseauth.presentation.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.jahidhasanco.firebaseauth.R
import dev.jahidhasanco.firebaseauth.presentation.screen.destinations.HomeScreenDestination
import dev.jahidhasanco.firebaseauth.presentation.screen.destinations.LoginScreenDestination
import dev.jahidhasanco.firebaseauth.presentation.viewmodel.AuthViewModel
import dev.jahidhasanco.firebaseauth.ui.theme.FirebaseAuthTheme
import dev.jahidhasanco.firebaseauth.ui.theme.primaryColor
import dev.jahidhasanco.firebaseauth.ui.theme.secondaryColor


@SuppressLint("StateFlowValueCalledInComposition")
@Destination
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val systemUiController = rememberSystemUiController()
    val scrollState = rememberScrollState()

    SideEffect {
        systemUiController.setStatusBarColor(primaryColor)
        systemUiController.setNavigationBarColor(secondaryColor)
    }

    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState(
        initialValue = authViewModel.user.value,
        key1 = lifecycle,
        key2 = authViewModel

    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            authViewModel.user.collect {
                value = it
                if (it.error.isNotBlank()) {
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let { user ->
                    navigator.navigate(HomeScreenDestination)
                    navigator.clearBackStack(LoginScreenDestination)
                    Toast.makeText(context, "Welcome ${user.email}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    Image(
        painter = painterResource(id = R.drawable.girl_pic_2),
        contentDescription = "Girl pic",
        modifier = Modifier
            .height(417.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop

    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState, enabled = true)
            .padding(top = 350.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        primaryColor,
                        secondaryColor
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Sign In", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 20.dp, end = 20.dp),
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )

            Text(
                text = "Sign In your account for exploring our services.", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, start = 20.dp, end = 20.dp),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )

            TextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp),
                label = { Text(text = "Email", color = Color.White) },
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White,
                    textColor = Color.White
                )
            )

            TextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp),
                label = { Text(text = "Password", color = Color.White) },
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White,
                    textColor = Color.White
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Text(
                text = "Forgot Password?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 20.dp, end = 20.dp),
                textAlign = TextAlign.End,
                color = Color.White,
            )

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
                    color = Color.White
                )
            }

            Button(
                onClick = {
                    authViewModel.login(email.value, password.value)
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
                    text = "Login", modifier = Modifier.padding(5.dp),
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    color = Color.Black
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    FirebaseAuthTheme {
//        LoginScreen(AuthViewModel(AuthRepository()),
//
//        )
    }
}