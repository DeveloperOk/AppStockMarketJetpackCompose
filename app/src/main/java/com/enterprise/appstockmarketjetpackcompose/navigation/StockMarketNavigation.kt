package com.enterprise.appstockmarketjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.enterprise.appstockmarketjetpackcompose.model.screen.StockDetailScreenData
import com.enterprise.appstockmarketjetpackcompose.model.screen.StockListScreenData
import com.enterprise.appstockmarketjetpackcompose.view.screen.stockdetail.StockDetailScreen
import com.enterprise.appstockmarketjetpackcompose.view.screen.stocklist.StockListScreen


@Composable
fun StockMarketNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = StockListScreenData
    ) {

        composable<StockListScreenData>{
            StockListScreen(navController = navController)
        }

        composable<StockDetailScreenData>{ backStackEntry ->

            val stockDetailScreenData: StockDetailScreenData = backStackEntry.toRoute()

            StockDetailScreen(stockDetailScreenData = stockDetailScreenData)
        }

    }

}