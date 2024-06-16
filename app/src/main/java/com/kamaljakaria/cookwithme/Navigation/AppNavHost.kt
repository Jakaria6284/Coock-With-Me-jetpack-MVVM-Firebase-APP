package com.kamaljakaria.cookwithme.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kamaljakaria.cookwithme.Screen.HomeScreen
import com.kamaljakaria.cookwithme.Screen.ItemScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("item/{id}", arguments = listOf(navArgument("id"){
      type= NavType.StringType
        })) {backStackEntry->
            val id=backStackEntry.arguments?.getString("id")
            if(id!=null)
            {
                ItemScreen(id=id,navController)
            }

        }
    }
}