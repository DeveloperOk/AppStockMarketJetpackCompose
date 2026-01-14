package com.enterprise.appstockmarketjetpackcompose.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
sealed interface StockMarketScreens {

    @Serializable
    data object StockListScreenRoute: StockMarketScreens, NavKey

    @Serializable
    data class StockDetailScreenRoute(var name: String, var currentPrice: Float): StockMarketScreens, NavKey

}