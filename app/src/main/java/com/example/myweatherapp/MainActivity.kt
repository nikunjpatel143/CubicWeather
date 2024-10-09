package com.example.myweatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import com.example.myweatherapp.views.DetailScreen
import com.example.myweatherapp.views.welcomeScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MyWeatherAppTheme {
                App()
            }
        }
    }
}


@Composable
fun App() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcomeScreen") {
        composable(route = "welcomeScreen") {
            welcomeScreen {
                navController.navigate("mainScreen/${it}")
            }
        }

        composable(route = "mainScreen/{city}", arguments = listOf(
            navArgument("city") {
                type = NavType.StringType
            }
        )) { it ->
            val city = it.arguments!!.getString("city")
            DetailScreen(city!!)

        }


    }
}







