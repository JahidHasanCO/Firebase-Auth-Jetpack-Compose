package dev.jahidhasanco.firebaseauth.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jahidhasanco.firebaseauth.R
import dev.jahidhasanco.firebaseauth.ui.theme.FirebaseAuthTheme
import dev.jahidhasanco.firebaseauth.ui.theme.primaryColor
import dev.jahidhasanco.firebaseauth.ui.theme.secondaryColor

@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

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
                )
            )

            Text(
                text = "Forgot Password?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 20.dp, end = 20.dp),
                textAlign = TextAlign.End,
                color = Color.White,
            )

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "Email: ${email.value} Password: ${password.value}",
                        Toast.LENGTH_SHORT
                    ).show()
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
        LoginScreen()
    }
}