package com.example.friendly_travel.screen.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.friendly_travel.R
import com.example.friendly_travel.screen.onboarding.components.OnBoardingData
import com.example.friendly_travel.ui.theme.Friendly_TravelTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

/**class MainActivity : ComponentActivity() {
    val colors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue
    )
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_200)
        setContent {
            Friendly_TravelTheme  {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {

                    val items = ArrayList<OnBoardingData>()

                    items.add(
                        OnBoardingData(
                            R.drawable.menu1,
                            "Shop Awesome Products",
                            "We have products in different categories including Apparels, Electronics, Accessories, Footwear etc."
                        )
                    )

                    items.add(
                        OnBoardingData(
                            R.drawable.menu2,
                            "One Day Delivery",
                            "Our delivery team around the clock to provide you the products fast and securely."
                        )
                    )

                    items.add(
                        OnBoardingData(
                            R.drawable.menu4,
                            "Amazing Customer Support",
                            "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface."
                        )
                    )

                    val pagerState = rememberPagerState(
                        pageCount = items.size,
                        initialOffscreenLimit = 2,
                        infiniteLoop = false,
                        initialPage = 0,
                    )

                    OnBoardingPager(
                        item = items, pagerState = pagerState, modifier = Modifier
                            .fillMaxWidth()
                    )

                }
            }
        }
    }


    @ExperimentalPagerApi
    @Composable
    fun OnBoardingPager(
        item: List<OnBoardingData>,
        pagerState: PagerState,
        modifier: Modifier = Modifier,
    ) {
        Box(modifier = modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                HorizontalPager(state = pagerState ) { page ->
                    Column(
                        modifier = Modifier
                            .padding(top = 60.dp)
                            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = item[page].image),
                            contentDescription = item[page].title,
                            modifier = Modifier
                                .height(250.dp)
                                .fillMaxWidth()
                        )

                        Text(
                            text = item[page].title,
                            modifier = Modifier.padding(top = 50.dp), color = Color.White,
                        )

                        Text(
                            text = item[page].desc,
                            modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                            color = Color.White,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )

                    }
                }

                PagerIndicator(item.size, pagerState.currentPage)
            }

            Box(modifier = Modifier.align(Alignment.BottomCenter)){
                BottomSection(pagerState.currentPage)
            }
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun rememberPagerState(
        @androidx.annotation.IntRange(from = 0) pageCount: Int,
        @androidx.annotation.IntRange(from = 0) initialPage: Int = 0,
        @FloatRange(from = 0.0, to = 1.0) initialPageOffset: Float = 0f,
        @androidx.annotation.IntRange(from = 1) initialOffscreenLimit: Int = 1,
        infiniteLoop: Boolean = false
    ): PagerState = rememberSaveable(saver = PagerState.Saver) {
        PagerState(
            //pageCount = pageCount,
            currentPage = initialPage,
            //currentPageOffset = initialPageOffset,
           // offscreenLimit = initialOffscreenLimit,
            //infiniteLoop = infiniteLoop
        )
    }

    @Composable
    fun PagerIndicator(size: Int, currentPage: Int) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 60.dp)
        ) {
            repeat(size) {
                Indicator(isSelected = it == currentPage, color = colors[it])
            }
        }
    }

    @Composable
    fun Indicator(isSelected: Boolean, color: Color) {
        val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)

        Box(
            modifier = Modifier
                .padding(1.dp)
                .height(10.dp)
                .width(width.value)
                .clip(CircleShape)
                .background(color = color)
        )
    }

    @Composable
    fun BottomSection(currentPager: Int) {
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = if (currentPager != 2) Arrangement.SpaceBetween else Arrangement.Center
        ) {

            if (currentPager == 2){
                OutlinedButton(
                    onClick = { },
                    shape = RoundedCornerShape(50), // = 40% percent
                ) {
                    Text(
                        text = "Get Started",
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp)
                    )
                }
            }else{
                SkipNextButton("Skip",Modifier.padding(start = 20.dp))
                SkipNextButton("Next",Modifier.padding(end = 20.dp))
            }

        }
    }

    @Composable
    fun SkipNextButton(text: String, modifier: Modifier) {
        Text(
            text = text, modifier = modifier, fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

    }
}*/