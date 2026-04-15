package com.example.gamemode

import rikka.shizuku.Shizuku

object Optimizer {

    fun applyUltraBoost() {
        exec("settings put global window_animation_scale 0")
        exec("cmd activity set-process-limit 3")
    }

    private fun exec(cmd: String) {
        val p = Shizuku.newProcess(arrayOf("sh","-c",cmd), null, null)
        p.waitFor()
    }
}
