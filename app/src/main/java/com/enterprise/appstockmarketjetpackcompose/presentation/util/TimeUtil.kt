package com.enterprise.appstockmarketjetpackcompose.presentation.util

import java.text.SimpleDateFormat
import java.util.Date


class TimeUtil {
    companion object{

        fun getCurrentFormattedTime(): String {

            val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
            val currentFormattedTime = simpleDateFormat.format(Date())
            return currentFormattedTime

        }

    }

}