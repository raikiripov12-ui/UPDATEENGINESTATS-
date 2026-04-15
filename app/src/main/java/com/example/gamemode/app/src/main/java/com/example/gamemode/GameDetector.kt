package com.example.gamemode

import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Handler
import android.os.Looper

object GameDetector {

    private val handler = Handler(Looper.getMainLooper())
    private var running = false

    private const val GAME = "com.dts.freefireth"

    fun start(context: Context) {
        running = true

        handler.post(object : Runnable {
            override fun run() {
                if (!running) return

                val app = getForeground(context)

                if (app == GAME) {
                    Optimizer.applyUltraBoost()
                    TemperatureMonitor.start(context)
                    LagMonitor.start()
                }

                handler.postDelayed(this, 4000)
            }
        })
    }

    fun stop() {
        running = false
    }

    private fun getForeground(context: Context): String? {
        val usm = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val time = System.currentTimeMillis()

        val stats = usm.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            time - 10000,
            time
        )

        return stats.maxByOrNull { it.lastTimeUsed }?.packageName
    }
}
