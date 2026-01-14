package com.enterprise.appstockmarketjetpackcompose.presentation.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.enterprise.appstockmarketjetpackcompose.presentation.navigation.StockMarketNavigation
import com.enterprise.appstockmarketjetpackcompose.presentation.ui.theme.AppStockMarketJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppStockMarketJetpackComposeTheme {
                StockMarketApp()
            }
        }
    }
}

@Composable
fun StockMarketApp() {

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(innerPadding)){

            StockMarketNavigation()

        }
    }

}

