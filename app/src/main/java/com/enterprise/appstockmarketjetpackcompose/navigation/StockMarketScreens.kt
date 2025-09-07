package com.enterprise.appstockmarketjetpackcompose.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class StockMarketScreens {

    // Define a StockListScreenRoute that doesn't take any arguments
    @Serializable
    object StockListScreenRoute: StockMarketScreens()

    @Serializable
    data class StockDetailScreenRoute(var name: String, var currentPrice: Float): StockMarketScreens()

}