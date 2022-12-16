package dev.jahidhasanco.firebaseauth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.jahidhasanco.firebaseauth.ui.theme.FirebaseAuthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseAuthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginUI()
                }
            }
        }
    }
}

@Composable
fun LoginUI() {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = email.value,
            onValueChange = {
                email.value = it
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            label = { Text(text = "Email") },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        TextField(
            value = password.value,
            onValueChange = {
                password.value = it
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            label = { Text(text = "Password") },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Text(
            text = "Forgot Password?", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.End
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
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "Login", modifier = Modifier.padding(5.dp),
                fontSize = MaterialTheme.typography.h6.fontSize
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginUIPreview() {
    FirebaseAuthTheme {
        LoginUI()
    }
}
