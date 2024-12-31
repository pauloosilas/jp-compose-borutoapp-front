package com.sumpaulo.borutoapp.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sumpaulo.borutoapp.ui.theme.DarkGray

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)

val LigthGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)

val ColorScheme.welcomeScreenBackgroundColor
    @Composable
    get() = if(isSystemInDarkTheme()) DarkGray else LigthGray


val ColorScheme.titleColor
    @Composable
    get() = if(isSystemInDarkTheme()) LigthGray else DarkGray


val ColorScheme.descriptionColor
    @Composable
    get() = if(isSystemInDarkTheme()) LigthGray.copy(alpha = 0.5f)  else DarkGray.copy(alpha = 0.5f)



val ColorScheme.activeIndicatorColor
    @Composable
    get() = if(isSystemInDarkTheme()) Purple500  else Purple700



val ColorScheme.inactiveIndicatorColor
    @Composable
    get() = if(isSystemInDarkTheme()) LigthGray else DarkGray

val ColorScheme.buttonBackgroundColor
    @Composable
    get() = if(isSystemInDarkTheme()) Purple500 else Purple700


