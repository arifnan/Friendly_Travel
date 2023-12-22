package com.example.friendly_travel.screen.favorite.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.friendly_travel.R

@Composable
fun WisataItem(
    id: Int,
    photo: Int,
    name: String,
    rating: Double,
    isFavorite: Boolean,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .wrapContentHeight(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFFFFF))
            ) {
                Image(
                    painter = painterResource(photo),
                    contentDescription = "wisata",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(16.dp).background(Color.White)) // Adding space between image and text content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(4.dp)) // Adding vertical space between text elements
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD700),
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(16.dp)
                    )
                    Text(
                        text = rating.toString(),
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
    Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
}

@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    MaterialTheme {
        WisataItem(
            id = 0,
            name = "pantaikuta",
            photo = R.drawable.pantaikuta,
            rating = 9.0,
            isFavorite = false,
            onFavoriteIconClicked = { id, newState ->
                println("Favorite icon clicked for best wisata with ID: $id. New state: $newState")
            }
        )
    }
}