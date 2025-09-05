package com.enterprise.appstockmarketjetpackcompose.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enterprise.appstockmarketjetpackcompose.R
import com.enterprise.appstockmarketjetpackcompose.repository.AppRepository
import com.enterprise.appstockmarketjetpackcompose.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class StockListScreenViewModel @Inject constructor(private val appRepository: AppRepository, @ApplicationContext context: Context): ViewModel(){

    private val TAG = "MainViewModel"

    val currentStockPriceListFlow: StateFlow<UiState> = appRepository
        .stockList
        .map{ stockPriceList ->
            UiState.Success(stockPriceList) as UiState
        }
        .retry { throwable ->
            Log.d(TAG, "Flow retry: ${throwable.message}")
            delay(3000)
            true
        }
        .onCompletion {
            Log.d(TAG, "Flow has completed.")
        }.catch{throwable ->
            Log.d(TAG, "Flow catch: ${throwable.message}")
            emit(UiState.Error(context.getString(R.string.stock_list_screen_error_message)))
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = UiState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
        )


}