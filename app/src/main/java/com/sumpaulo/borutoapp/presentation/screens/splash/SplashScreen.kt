package com.sumpaulo.borutoapp.presentation.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sumpaulo.borutoapp.ui.theme.Purple500
import com.sumpaulo.borutoapp.ui.theme.Purple700
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.rotate
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sumpaulo.borutoapp.R
import androidx.compose.runtime.getValue
import com.sumpaulo.borutoapp.navigation.Screen

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
){
    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    val degrees = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )

        navController.popBackStack()
        if(onBoardingCompleted){
            navController.navigate(Screen.Home.route)
        }else {
            navController.navigate(Screen.Welcome.route)
        }
    }

    Splash(degrees=degrees.value)
}

@Composable
fun Splash(degrees: Float){
    if(isSystemInDarkTheme()){
        Box(modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Image(
                modifier = Modifier.rotate(degrees = degrees),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        }
    }
    else{
        Box(modifier = Modifier
            .background(Brush.verticalGradient(listOf(Purple700, Purple500)))
            .fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Image(
                modifier = Modifier.rotate(degrees = degrees),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        }
    }
}

@Composable
@Preview
fun SplaceScreenPreview(){
    Splash(100f)
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SplaceScreenDarkPreview(){
    Splash(50f)
}