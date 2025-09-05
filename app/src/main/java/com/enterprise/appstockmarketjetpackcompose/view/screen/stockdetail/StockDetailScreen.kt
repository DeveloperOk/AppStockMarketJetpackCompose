package com.enterprise.appstockmarketjetpackcompose.view.screen.stockdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.enterprise.appstockmarketjetpackcompose.R
import com.enterprise.appstockmarketjetpackcompose.model.screen.StockDetailScreenData
import com.enterprise.appstockmarketjetpackcompose.ui.theme.StockDetailLabelTextColor

@Composable
fun StockDetailScreen(
    stockDetailScreenData: StockDetailScreenData
) {


       Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){

        val stockNameText = buildAnnotatedString {
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold, color = StockDetailLabelTextColor))
            append(stringResource(id = R.string.stock_detail_screen_stock_name_label))
            pop()
            pushStyle(SpanStyle(color = StockDetailLabelTextColor))
            append(stockDetailScreenData.name)
            pop()
        }

        val stockPriceText = buildAnnotatedString {
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold, color = StockDetailLabelTextColor))
            append(stringResource(id = R.string.stock_detail_screen_price_label))
            pop()
            pushStyle(SpanStyle(color = StockDetailLabelTextColor))
            append(stringResource(id = R.string.stock_detail_screen_usd_sign)
                    + stockDetailScreenData.currentPrice.toString())
            pop()
        }

        Image(painter = painterResource(R.drawable.stock_detail_image),
            contentDescription = "Stock Detail Image")

        Spacer(modifier = Modifier.height( 5.dp))
        Text(text = stockNameText, modifier = Modifier.padding(horizontal = 5.dp))

        Spacer(modifier = Modifier.height( 5.dp))
        Text(text = stockPriceText, modifier = Modifier.padding(horizontal = 5.dp))

    }

}