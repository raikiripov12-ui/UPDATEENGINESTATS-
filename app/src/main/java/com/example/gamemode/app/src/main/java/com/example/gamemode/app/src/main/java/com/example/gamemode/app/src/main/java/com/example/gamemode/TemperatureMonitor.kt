package com.example.gamemode

import android.content.Context
import android.os.BatteryManager

object TemperatureMonitor {
    var currentTemp = 0f

    fun start(context: Context) {
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        currentTemp = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_TEMPERATURE) / 10f
    }
}
