package com.enterprise.appstockmarketjetpackcompose.view.screen.stocklist


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.enterprise.appstockmarketjetpackcompose.remotedatasource.mock.Stock
import com.enterprise.appstockmarketjetpackcompose.state.UiState
import com.enterprise.appstockmarketjetpackcompose.ui.theme.CircularProgressIndicatorColor
import com.enterprise.appstockmarketjetpackcompose.viewmodel.StockListScreenViewModel


@Composable
fun StockListScreen(
    navController: NavHostController,
    stockListScreenViewModel: StockListScreenViewModel
) {

    val uiState = stockListScreenViewModel.currentStockPriceListFlow.collectAsStateWithLifecycle()

    val context = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){

        when (uiState.value) {
            is UiState.Loading -> {

                CircularProgressIndicator(color = CircularProgressIndicatorColor)

            }
            is UiState.Success -> {

                val uiStateSuccess = uiState.value as UiState.Success
                val stockList = uiStateSuccess.stockList as ArrayList<Stock>

                LazyColumn(modifier = Modifier.fillMaxSize()){

                    items(stockList){ stock ->


                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically){

                            Text(stock.name)
                            Text(stock.currentPrice.toString())

                        }


                    }

                }

            }
            is UiState.Error -> {
                val uiStateError= uiState.value as UiState.Error
                Toast.makeText(context, uiStateError.message, Toast.LENGTH_LONG).show()
            }


        }

    }

}