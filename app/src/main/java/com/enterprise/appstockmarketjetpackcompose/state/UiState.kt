package com.enterprise.appstockmarketjetpackcompose.state

import com.enterprise.appstockmarketjetpackcompose.remotedatasource.mock.Stock


sealed class UiState {
    object Loading : UiState()
    data class Success(val stockList: List<Stock>) : UiState()
    data class Error(val message: String) : UiState()
}