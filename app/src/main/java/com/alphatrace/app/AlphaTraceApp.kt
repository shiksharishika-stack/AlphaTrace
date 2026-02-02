package com.alphatrace.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.alphatrace.app.data.ProgressRepository
import com.alphatrace.app.navigation.AlphaTraceNavGraph
import com.alphatrace.app.ui.theme.AlphaTraceTheme

@Composable
fun AlphaTraceApp(progressRepository: ProgressRepository) {
    AlphaTraceTheme {
        val navController = rememberNavController()
        AlphaTraceNavGraph(
            navController = navController,
            progressRepository = progressRepository
        )
    }
}
