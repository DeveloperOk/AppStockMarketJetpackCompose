package com.enterprise.appstockmarketjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.enterprise.appstockmarketjetpackcompose.model.screen.StockDetailScreenData
import com.enterprise.appstockmarketjetpackcompose.view.screen.stockdetail.StockDetailScreen
import com.enterprise.appstockmarketjetpackcompose.view.screen.stocklist.StockListScreen
import com.enterprise.appstockmarketjetpackcompose.viewmodel.StockListScreenViewModel


@Composable
fun StockMarketNavigation(
) {

    //With rememberNavBackStack, backstack survives the configuration changes such as screen rotation etc.
    val backStack = rememberNavBackStack(
        //NavigationStartScreen
        StockMarketScreens.StockListScreenRoute
    )

    NavDisplay(
        backStack = backStack,

        onBack = { backStack.removeLastOrNull() },

        //In order to clear viewmodel, on navigation back
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),

        entryProvider = entryProvider {
            entry<StockMarketScreens.StockListScreenRoute> {

                val stockListScreenViewModel = hiltViewModel<StockListScreenViewModel>()

                StockListScreen(navigateToStockDetailScreen = { stock ->

                    backStack.add(
                        StockMarketScreens.StockDetailScreenRoute(name = stock.name,
                            currentPrice = stock.currentPrice)
                    )

                }, stockListScreenViewModel = stockListScreenViewModel)

            }

            entry<StockMarketScreens.StockDetailScreenRoute> { stockDetailScreenRoute ->

                val stockDetailScreenData =
                    StockDetailScreenData(name = stockDetailScreenRoute.name,
                        currentPrice = stockDetailScreenRoute.currentPrice)

                StockDetailScreen(stockDetailScreenData = stockDetailScreenData)

            }
        }
    )
}