package com.example.friendly_travel.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.friendly_travel.R
import com.example.friendly_travel.di.Injection
import com.example.friendly_travel.state.UiState
import com.example.friendly_travel.viewmodel.DetailViewModel
import com.example.friendly_travel.viewmodel.ViewModelFactory

@Composable
fun DetailWisataScreen(
    wisataId:Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel( factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getWisataById(wisataId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailInformation(
                    photo = data.photo,
                    id = data.id,
                    name = data.name,
                    alamat = data.alamat,
                    description = data.deskripsi,
                    rating = data.rating,
                    isFavorite = data.isFavorite,
                    navigateBack = navigateBack,
                    onFavoriteButtonClicked = { id, state ->
                        viewModel.updateWisata(id, state)
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailInformation(
    id: Int,
    name: String,
    photo: Int,
    description: String,
    alamat: String,
    isFavorite: Boolean,
    navigateBack: () -> Unit,
    onFavoriteButtonClicked: (id: Int, state: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    rating: Double,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(296.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(photo),
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("scrollToBottom")
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = alamat,
                    modifier = Modifier.padding(start = 2.dp, end = 8.dp),
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = rating.toString(),
                    modifier = Modifier.padding(start = 2.dp, end = 8.dp),
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = description,
                    modifier = Modifier.padding(start = 2.dp, end = 8.dp),
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(5.dp))
        }

        IconButton(
            onClick = navigateBack,
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .size(40.dp)
                .background(Color(0xFF2D9632))
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                tint = Color.White
            )
        }

        IconButton(
            onClick = {
                onFavoriteButtonClicked(id, isFavorite)
            },
            modifier = Modifier
                .padding(end = 16.dp, top = 8.dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .size(40.dp)
                .background(Color(0xFF2D9632))
        ) {
            Icon(
                imageVector = if (!isFavorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                contentDescription = if (!isFavorite) stringResource(R.string.add_favorite) else stringResource(
                    R.string.delete_favorite
                ),
                tint = if (!isFavorite) Color.Black else Color.Red
            )
        }
    }

}