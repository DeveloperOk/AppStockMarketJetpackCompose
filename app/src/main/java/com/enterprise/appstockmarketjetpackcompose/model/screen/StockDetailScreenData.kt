package com.enterprise.appstockmarketjetpackcompose.model.screen

import kotlinx.serialization.Serializable

@Serializable
data class StockDetailScreenData(var name: String, var currentPrice: Float)