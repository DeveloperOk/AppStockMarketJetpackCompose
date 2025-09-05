package com.enterprise.appstockmarketjetpackcompose.view.screen.stocklist


import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.enterprise.appstockmarketjetpackcompose.R
import com.enterprise.appstockmarketjetpackcompose.remotedatasource.mock.PriceTrend
import com.enterprise.appstockmarketjetpackcompose.remotedatasource.mock.Stock
import com.enterprise.appstockmarketjetpackcompose.state.UiState
import com.enterprise.appstockmarketjetpackcompose.ui.theme.CircularProgressIndicatorColor
import com.enterprise.appstockmarketjetpackcompose.ui.theme.LastUpdateTimeColor
import com.enterprise.appstockmarketjetpackcompose.ui.theme.StockListRowDownBorderColor
import com.enterprise.appstockmarketjetpackcompose.ui.theme.StockListRowNeutralBorderColor
import com.enterprise.appstockmarketjetpackcompose.ui.theme.StockListRowTextColor
import com.enterprise.appstockmarketjetpackcompose.ui.theme.StockListRowUpBorderColor
import com.enterprise.appstockmarketjetpackcompose.util.TimeUtil
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

                StockListScreenProgressIndicator()

            }
            is UiState.Success -> {

                TimeAndStockList(uiState = uiState)

            }
            is UiState.Error -> {

                val uiStateError= uiState.value as UiState.Error
                Toast.makeText(context, uiStateError.message, Toast.LENGTH_LONG).show()

            }


        }

    }

}

@Composable
fun StockListScreenProgressIndicator(){

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){

        CircularProgressIndicator(color = CircularProgressIndicatorColor)

    }

}

@Composable
fun TimeAndStockList(uiState: State<UiState>) {

    val uiStateSuccess = uiState.value as UiState.Success
    val stockList = uiStateSuccess.stockList as ArrayList<Stock>

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {

        Text(text = stringResource(id = R.string.stock_list_screen_last_update_time)
        + TimeUtil.getCurrentFormattedTime(),
            fontSize = 14.sp,
            color = LastUpdateTimeColor)

        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1F)) {

            items(stockList) { stock ->

                StockListRow(stock = stock)

            }

        }

    }
}

@Composable
fun StockListRow(stock: Stock) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .padding(2.dp)
            .border(width = 2.dp,color = getBorderColor(stock = stock), shape = RoundedCornerShape(10.dp))
            .padding(5.dp)
            .horizontalScroll(rememberScrollState())
    ) {

        Row(verticalAlignment = Alignment.CenterVertically){

            Text(text = stock.rank.toString(),
                color = StockListRowTextColor,
                fontSize = 14.sp,
                modifier = Modifier.padding(2.dp)
            )

            Text(text = stock.name,
                color = StockListRowTextColor,
                fontSize = 14.sp,
                modifier = Modifier.padding(2.dp))

        }

        Row(verticalAlignment = Alignment.CenterVertically){

            Image(painter = painterResource(id = getPriceTrendDrawableID(stock = stock)),
                contentDescription = "Price Trend")

            Text(text = stringResource(R.string.stock_list_screen_usd_sign) + stock.currentPrice,
                color = StockListRowTextColor,
                fontSize = 14.sp,
                modifier = Modifier.padding(2.dp))

        }

    }

}


fun getPriceTrendDrawableID(stock: Stock): Int {

        var drawableID = R.drawable.arrow_neutral_no_image
        when (stock.priceTrend) {
            PriceTrend.UP -> {
                drawableID = R.drawable.arrow_upward
            }

            PriceTrend.DOWN -> {
                drawableID = R.drawable.arrow_downward
            }

            PriceTrend.NEUTRAL -> {
                drawableID = R.drawable.arrow_neutral_no_image
            }

            PriceTrend.UNKNOWN -> {
                drawableID = R.drawable.arrow_neutral_no_image
            }
            else -> {
                drawableID = R.drawable.arrow_neutral_no_image
            }
        }

       return drawableID

}


private fun getBorderColor(
    stock: Stock,
): Color {
    var outputColor = StockListRowNeutralBorderColor
    when (stock.priceTrend) {
        PriceTrend.UP -> {
            outputColor = StockListRowUpBorderColor
        }
        PriceTrend.DOWN -> {
            outputColor = StockListRowDownBorderColor

        }
        PriceTrend.NEUTRAL -> {
            outputColor = StockListRowNeutralBorderColor

        }
        PriceTrend.UNKNOWN -> {
            outputColor = StockListRowNeutralBorderColor
        }
        else -> {
            outputColor = StockListRowNeutralBorderColor

        }
    }

    return outputColor

}
