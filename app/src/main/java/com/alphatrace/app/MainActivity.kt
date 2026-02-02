package com.alphatrace.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alphatrace.app.data.ProgressRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val progressRepository = ProgressRepository(applicationContext)
        setContent {
            AlphaTraceApp(progressRepository = progressRepository)
        }
    }
}
