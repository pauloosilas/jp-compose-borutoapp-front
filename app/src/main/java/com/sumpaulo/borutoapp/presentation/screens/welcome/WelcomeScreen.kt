package com.sumpaulo.borutoapp.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sumpaulo.borutoapp.R
import com.sumpaulo.borutoapp.domain.model.OnBoardingPage
import com.sumpaulo.borutoapp.navigation.Screen
import com.sumpaulo.borutoapp.ui.theme.EXTRA_LARGE_PADDING
import com.sumpaulo.borutoapp.ui.theme.SMALL_PADDING
import com.sumpaulo.borutoapp.ui.theme.activeIndicatorColor
import com.sumpaulo.borutoapp.ui.theme.buttonBackgroundColor
import com.sumpaulo.borutoapp.ui.theme.descriptionColor
import com.sumpaulo.borutoapp.ui.theme.inactiveIndicatorColor
import com.sumpaulo.borutoapp.ui.theme.titleColor
import com.sumpaulo.borutoapp.ui.theme.welcomeScreenBackgroundColor
import com.sumpaulo.borutoapp.util.Constants.LAST_ON_BOARDING_PAGE


@Composable
fun WelcomeScreen(navController: NavHostController,
                  welcomeViewModel: WelcomeViewModel = hiltViewModel()){
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val pagerState = rememberPagerState{ pages.size }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.welcomeScreenBackgroundColor)
    ){
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            PagerScreen(onBoardingPage = pages[page])
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(vertical =  60.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration)
                                MaterialTheme.colorScheme.activeIndicatorColor
                            else MaterialTheme.colorScheme.inactiveIndicatorColor
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(12.dp)
                )
            }
        }

        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState)
        {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(completed = true)
        }



    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.6f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text=onBoardingPage.title,
            color = MaterialTheme.colorScheme.titleColor,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text=onBoardingPage.description,
            color = MaterialTheme.colorScheme.descriptionColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState:PagerState,
    onClick: () -> Unit
){
    Row(modifier = modifier
        .padding(horizontal = EXTRA_LARGE_PADDING),
        //verticalAlignment = Arrangement.Top as Alignment.Vertical,
        horizontalArrangement = Arrangement.Center
        )
    {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == LAST_ON_BOARDING_PAGE) {
            Button(onClick = onClick,
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.buttonBackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text("Finish")
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Composable
@Preview(showSystemUi = true)
fun SecondOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Composable
@Preview(showSystemUi = true)
fun ThirdOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}