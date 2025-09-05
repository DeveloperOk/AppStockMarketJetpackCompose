package com.enterprise.appstockmarketjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.enterprise.appstockmarketjetpackcompose.model.screen.StockDetailScreenData
import com.enterprise.appstockmarketjetpackcompose.model.screen.StockListScreenData
import com.enterprise.appstockmarketjetpackcompose.view.screen.stockdetail.StockDetailScreen
import com.enterprise.appstockmarketjetpackcompose.view.screen.stocklist.StockListScreen
import com.enterprise.appstockmarketjetpackcompose.viewmodel.StockListScreenViewModel


@Composable
fun StockMarketNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = StockListScreenData
    ) {

        composable<StockListScreenData>{

            val stockListScreenViewModel = hiltViewModel<StockListScreenViewModel>()

            StockListScreen(navController = navController, stockListScreenViewModel = stockListScreenViewModel)
        }

        composable<StockDetailScreenData>{ backStackEntry ->

            val stockDetailScreenData: StockDetailScreenData = backStackEntry.toRoute()

            StockDetailScreen(stockDetailScreenData = stockDetailScreenData)
        }

    }

}