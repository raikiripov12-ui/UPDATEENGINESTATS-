package com.example.gamemode

import android.app.*
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class GameService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, createNotification())
        GameDetector.start(this)
        return START_STICKY
    }

    override fun onDestroy() {
        GameDetector.stop()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotification(): Notification {
        val channelId = "game_mode"

        val channel = NotificationChannel(
            channelId,
            "Game Mode",
            NotificationManager.IMPORTANCE_LOW
        )

        getSystemService(NotificationManager::class.java)
            .createNotificationChannel(channel)

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Modo activo")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .build()
    }
}
