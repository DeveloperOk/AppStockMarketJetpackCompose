package com.enterprise.appstockmarketjetpackcompose.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class StockDetailScreenData(var name: String, var currentPrice: Float)