package ru.zar1official.hackathon_final.presentation.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.zar1official.hackathon_final.R
import ru.zar1official.hackathon_final.presentation.screens.main.BottomBarScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
    }, bottomBar = {
        BottomBar(navController = navController)
    }, scaffoldState = scaffoldState) {
        BottomNavGraph(navController = navController, scaffoldState = scaffoldState)
    }
}

@Composable
fun BottomBar(navController: NavController) {
    BottomNavigation {
        val screens = listOf(BottomBarScreen.WorkSpace, BottomBarScreen.ChillSpace)
        screens.forEach { screen ->
            BottomNavigationItem(
                selected = false,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        popUpTo(screen.route)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = stringResource(id = screen.contentDescription)
                    )
                },
                label = { Text(text = stringResource(id = screen.title)) })
        }
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController, scaffoldState: ScaffoldState) {
    NavHost(navController = navController, startDestination = BottomBarScreen.WorkSpace.route) {
        composable(
            route = BottomBarScreen.WorkSpace.route,
        ) {
            WorkSpaceScreen(scaffoldState = scaffoldState)
        }
        composable(route = BottomBarScreen.ChillSpace.route) {
            ChillSpaceScreen(scaffoldState = scaffoldState)
        }
    }
}