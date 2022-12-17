package dev.jahidhasanco.firebaseauth.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.jahidhasanco.firebaseauth.R
import dev.jahidhasanco.firebaseauth.ui.theme.primaryColorAlpha


@Composable
fun WelcomeScreen() {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

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

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}