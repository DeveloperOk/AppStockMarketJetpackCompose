package com.enterprise.appstockmarketjetpackcompose.view.screen.stockdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.enterprise.appstockmarketjetpackcompose.model.screen.StockDetailScreenData

@Composable
fun StockDetailScreen(stockDetailScreenData: StockDetailScreenData) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){

        Text(text = stockDetailScreenData.title)
        Text(text = stockDetailScreenData.message)

    }

}