package com.example.friendly_travel.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.friendly_travel.R
import com.example.friendly_travel.components.CategoryItem
import com.example.friendly_travel.components.EmptyList
import com.example.friendly_travel.components.Menu
import com.example.friendly_travel.components.MenuItem
import com.example.friendly_travel.components.dummyCategory
import com.example.friendly_travel.di.Injection
import com.example.friendly_travel.model.Wisata
import com.example.friendly_travel.screen.favorite.ListWisata
import com.example.friendly_travel.screen.favorite.components.WisataItem
import com.example.friendly_travel.screen.search.components.Search
import com.example.friendly_travel.state.UiState
import com.example.friendly_travel.viewmodel.HomeViewModel
import com.example.friendly_travel.viewmodel.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {

    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.search(query)
            }
            is UiState.Success -> {
                HomeContent(
                    query = query,
                    onQueryChange = viewModel::search,
                    listWisata = uiState.data,
                    onFavoriteIconClicked = { id, newState ->
                        viewModel.updateWisata(id, newState)
                    },
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category)
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title }) { menu ->
            MenuItem(menu)
        }
    }
}

@Composable
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listWisata: List<Wisata>,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
            .padding(16.dp)
    ) {
        Search(
            query = query,
            onQueryChange = onQueryChange,
            modifier = Modifier
                .background(Color(0xFFFFFFFF))
                .padding(16.dp)
        )

        if (listWisata.isNotEmpty()) {
            ListWisata(
                listWisata = listWisata,
                onFavoriteIconClicked = onFavoriteIconClicked,
                navigateToDetail = navigateToDetail,
                contentPaddingTop = 16.dp
            )
        } else {
            EmptyList(
                warning = stringResource(R.string.empty_data),
                modifier = Modifier
                    .testTag("emptyList")
                    .fillMaxSize()
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
                    isFavorite = false,
                    onFavoriteIconClicked = { id, newState ->
                        println("Favorite icon clicked for best game with ID: $id. New state: $newState")
                    }
                )
            }
        }
    }
}
