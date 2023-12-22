package com.example.friendly_travel.screen.favorite

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.friendly_travel.R
import com.example.friendly_travel.components.EmptyList
import com.example.friendly_travel.di.Injection
import com.example.friendly_travel.model.Wisata
import com.example.friendly_travel.screen.favorite.components.WisataItem
import com.example.friendly_travel.state.UiState
import com.example.friendly_travel.viewmodel.FavoriteViewModel
import com.example.friendly_travel.viewmodel.ViewModelFactory

@Composable
fun FavoriteScreen(
    navigateToDetail: (Int) ->Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteFilm()
            }
            is UiState.Success -> {
                FavoriteInformation(
                    listWisata = uiState.data,
                    navigateToDetail = navigateToDetail,
                    onFavoriteIconClicked = { id, newState ->
                        viewModel.updatePlayer(id, newState)
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteInformation(
    listWisata: List<Wisata>,
    navigateToDetail: (Int) -> Unit,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(Color(0xFFFFFFFF))
    ) {
        if (listWisata.isNotEmpty()) {
            ListWisata(
                listWisata= listWisata,
                onFavoriteIconClicked = onFavoriteIconClicked,
                contentPaddingTop = 16.dp,
                navigateToDetail = navigateToDetail
            )
        } else {
            EmptyList(
                warning = stringResource(R.string.empty_favorite)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListWisata(
    listWisata: List<Wisata>,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    contentPaddingTop: Dp = 0.dp,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
            top = contentPaddingTop
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .background(Color(0xFFFFFFFF))
            .testTag("lazy_list")
    ) {
        itemsIndexed(listWisata) { index, item ->
            key(item.id) {
                WisataItem(
                    id = item.id,
                    name = item.name,
                    photo = item.photo,
                    rating = item.rating,
                    isFavorite = item.isFavorite,
                    onFavoriteIconClicked = onFavoriteIconClicked,
                    modifier = Modifier
                        .animateItemPlacement(tween(durationMillis = 200))
                        .clickable { navigateToDetail(item.id) }
                )
            }
        }
    }
}