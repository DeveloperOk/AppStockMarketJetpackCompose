package com.enterprise.appstockmarketjetpackcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enterprise.appstockmarketjetpackcompose.presentation.ui.theme.AppPrimaryColor
import com.enterprise.appstockmarketjetpackcompose.presentation.ui.theme.AppStockMarketJetpackComposeTheme
import com.enterprise.appstockmarketjetpackcompose.presentation.view.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val activityFinisher : () -> Unit = {
            this.finish()
        }

        setContent {
            AppStockMarketJetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center,
                           modifier = Modifier.fillMaxSize().padding(innerPadding)){

                        SplashScreen(activityFinisher = activityFinisher)

                    }
                }
            }
        }
    }
}



@Composable
fun SplashScreen(activityFinisher: () -> Unit) {

    val context = LocalContext.current

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )

        delay(2000L)
        launchNextScreen(context = context, activityFinisher = activityFinisher)
    } )


    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(color = Color.White)) {


        Surface(
            modifier = Modifier
                .padding(15.dp)
                .size(330.dp)
                .scale(scale.value),
            shape = CircleShape,
            color = Color.White,
            border = BorderStroke(
                width = 2.dp, color = AppPrimaryColor
            )
        ) {
            Column(
                modifier = Modifier.padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splash_screen_image),
                    contentDescription = "book",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(95.dp)
                )
                Text(
                    text = stringResource(R.string.splash_screen_message),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.LightGray
                )
            }


        }
    }
}

fun launchNextScreen(context: Context, activityFinisher: () -> Unit) {

    val mainActivityIntent = Intent(context, MainActivity::class.java)
    context.startActivity(mainActivityIntent)
    activityFinisher()
}



