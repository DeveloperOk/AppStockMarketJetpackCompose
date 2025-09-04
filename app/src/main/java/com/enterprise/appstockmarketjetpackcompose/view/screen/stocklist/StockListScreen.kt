package com.enterprise.appstockmarketjetpackcompose.view.screen.stocklist


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.enterprise.appstockmarketjetpackcompose.model.screen.StockDetailScreenData
import kotlinx.coroutines.delay


@Composable
fun StockListScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true, block = {

        delay(5000L)

        navController.navigate(
            StockDetailScreenData(title = "Stock Detail Screen",
                message = "Test Successful!")
        )

    } )

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){

        Text(text = "Stock List Screen")

    }

}