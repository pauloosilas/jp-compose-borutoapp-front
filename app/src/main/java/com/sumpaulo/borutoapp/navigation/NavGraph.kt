package com.sumpaulo.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sumpaulo.borutoapp.presentation.screens.home.HomeScreen
import com.sumpaulo.borutoapp.presentation.screens.splash.SplashScreen
import com.sumpaulo.borutoapp.presentation.screens.splash.SplashViewModel
import com.sumpaulo.borutoapp.presentation.screens.welcome.WelcomeScreen
import com.sumpaulo.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SetupNavGraph(navController: NavHostController, splashViewModel: SplashViewModel){
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){
            SplashScreen(navController, splashViewModel)
        }

        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Home.route){
            HomeScreen()
        }

        composable(route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY){
               type = NavType.IntType
            })
        ){

        }


        composable(route = Screen.Search.route){

        }
    }
}