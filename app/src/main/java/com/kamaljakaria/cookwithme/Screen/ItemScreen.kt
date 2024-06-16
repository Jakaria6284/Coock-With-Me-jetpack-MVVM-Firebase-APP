package com.kamaljakaria.cookwithme.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.kamaljakaria.cookwithme.Data.Recipe
import com.kamaljakaria.cookwithme.Data.cheifdata
import com.kamaljakaria.cookwithme.ViewModel.viewModel
import kotlin.random.Random

@Composable
fun ItemScreen(id: String, navController: NavHostController) {
    val viewModel: viewModel = viewModel()

    viewModel.fetchChefData()
    viewModel.fetchRecipesForChef(id)

    val items by viewModel.items.observeAsState(emptyList())
    val recipes by viewModel.recipes.observeAsState(emptyList())

    val randomColor = Color(
        Random.nextFloat() * 0.5f + 0.5f,
        Random.nextFloat() * 0.5f + 0.5f,
        Random.nextFloat() * 0.5f + 0.5f,
        1f
    )

    val selectedChef = items.firstOrNull { it.id == id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(randomColor)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(380.dp, 480.dp)
                .padding(20.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(randomColor)
        ) {
            selectedChef?.let { chef ->
                Image(
                    painter = rememberImagePainter(data = chef.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(25.dp))
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                start = Offset(0f, 0f),
                                end = Offset(0f, Float.POSITIVE_INFINITY)
                            )
                        ),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = "Explore ${chef.name}'s delicious Recipe",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W900,
                        ),
                        modifier = Modifier.padding(start = 40.dp, bottom = 20.dp)
                    )
                }
            }
        }

        LazyColumn(modifier = Modifier.padding(5.dp)) {
            itemsIndexed(recipes) { index, recipe ->
                lazycolumnitemm(recipe = recipe) {
                    // Handle item click if needed
                }
            }
        }
    }
}

@Composable
fun lazycolumnitemm(recipe: Recipe, onItemClick: (cheifdata) -> Unit) {
    val randomColor = Color(
        Random.nextFloat() * 0.5f + 0.5f,
        Random.nextFloat() * 0.5f + 0.5f,
        Random.nextFloat() * 0.5f + 0.5f,
        1f
    )

    Row(
        modifier = Modifier
            .size(390.dp, 120.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp, 120.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(randomColor)
        ) {
            Image(
                painter = rememberImagePainter(data = recipe.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(25.dp))
            )
        }

        Text(
            text = recipe.name,
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W900,
            ),
            modifier = Modifier.padding(start = 30.dp, bottom = 20.dp, top = 30.dp)
        )
    }

    Spacer(modifier = Modifier.height(10.dp))
}
