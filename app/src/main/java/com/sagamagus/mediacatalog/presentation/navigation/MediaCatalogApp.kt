package com.sagamagus.mediacatalog.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sagamagus.mediacatalog.presentation.home.HomeScreen
import com.sagamagus.mediacatalog.presentation.detail.DetailScreen
import com.sagamagus.mediacatalog.presentation.trailer.TrailerScreen

@Composable
fun MediaCatalogApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                onItemClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }

        composable(
            route = "detail/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: 0

            DetailScreen(id = id,
                navController = navController)
        }

        composable(
            route = "trailer?url={url}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val url = backStackEntry.arguments?.getString("url") ?: ""

            TrailerScreen(
                videoUrl = url,
                onBack = { navController.popBackStack() }
            )
        }
    }
}