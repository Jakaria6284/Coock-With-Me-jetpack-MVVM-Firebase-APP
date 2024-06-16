package com.kamaljakaria.cookwithme.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import coil.compose.rememberImagePainter
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.kamaljakaria.cookwithme.Data.cheifdata

import com.kamaljakaria.cookwithme.R
import com.kamaljakaria.cookwithme.ViewModel.viewModel
import kotlin.random.Random


@Composable
fun HomeScreen( navController: NavHostController) {
    val viewModel: viewModel= viewModel()
    viewModel.fetchChefData()
    val items by viewModel.items.observeAsState(emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x0FFFFFFF))
    ) {
        Row(modifier = Modifier.padding(start = 10.dp)) {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "10 oct", style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.W400,
                    )
                )

                Text(
                    text = "Hi, Ilham", style = TextStyle(
                        color = Color.Black,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.W900,
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.imggggr),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp, 100.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(25.dp))
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Best Chef", style = TextStyle(
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.W700,
            ),
            modifier = Modifier.padding(start = 10.dp)
        )

        LazyRow(modifier = Modifier.padding(5.dp)) {

            itemsIndexed(items) { index, item ->
                lazycolumnitem(item = item) {
                    navController.navigate("item/${item.id}")
                }
            }
        }
    }
}

@Composable
fun lazycolumnitem(item: cheifdata, onItemClick: (cheifdata) -> Unit) {
    val randomColor = Color(
        Random.nextFloat() * 0.5f + 0.5f,  // Ensures the value is between 0.5 and 1
        Random.nextFloat() * 0.5f + 0.5f,  // Ensures the value is between 0.5 and 1
        Random.nextFloat() * 0.5f + 0.5f,  // Ensures the value is between 0.5 and 1
        1f
    )

    Box(
        modifier = Modifier
            .size(300.dp, 500.dp)
            .padding(20.dp)
            .clip(RoundedCornerShape(25.dp)) // Clip the entire column
            .background(randomColor) // Apply random background color
            .clickable { onItemClick(item) } // Add clickable modifier
    ) {


        Image(
            painter = rememberImagePainter(data = item.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(25.dp)) // Clip the image within the column
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        start = Offset(0f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY) // Adjust direction as needed
                    )
                ),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = item.name, style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W900,
                ),
                modifier = Modifier.padding(start = 30.dp, bottom = 20.dp)
            )
        }
    }
}


