package com.alphatrace.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alphatrace.app.data.ProgressRepository
import com.alphatrace.app.ui.home.HomeScreen
import com.alphatrace.app.ui.tracing.TracingScreen

object Routes {
    const val HOME = "home"
    const val TRACING = "tracing/{letter}"
    fun tracing(letter: Char) = "tracing/$letter"
}

@Composable
fun AlphaTraceNavGraph(
    navController: NavHostController,
    progressRepository: ProgressRepository
) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                progressRepository = progressRepository,
                onLetterClick = { letter ->
                    navController.navigate(Routes.tracing(letter))
                }
            )
        }
        composable(
            route = Routes.TRACING,
            arguments = listOf(navArgument("letter") { type = NavType.StringType })
        ) { backStackEntry ->
            val letter = backStackEntry.arguments?.getString("letter")?.first() ?: 'A'
            TracingScreen(
                letter = letter,
                progressRepository = progressRepository,
                onBack = { navController.popBackStack() },
                onNextLetter = {
                    val next = if (letter < 'Z') letter + 1 else 'A'
                    navController.popBackStack()
                    navController.navigate(Routes.tracing(next))
                }
            )
        }
    }
}
