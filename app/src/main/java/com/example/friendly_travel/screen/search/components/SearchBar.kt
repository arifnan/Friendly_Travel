package com.example.friendly_travel.screen.search.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.friendly_travel.R

/**@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(onButton1Click: () -> Unit, onButton2Click: () -> Unit,onBackPressed: () -> Unit) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // LongSearchBar
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            },
            modifier = Modifier
                .weight(2f)
                .height(48.dp)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(24.dp))
                .clip(RoundedCornerShape(24.dp))
                .border(3.dp, Color.Black),
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            leadingIcon = { Icons.Default.Search },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            placeholder = {
                Text(stringResource(R.string.search))
            },
            shape = RoundedCornerShape(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Button 1
        Button(
            onClick = { onButton1Click() },
            modifier = Modifier
                .width(30.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(3.dp, Color.Black)
                .padding(start = 2.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_filter),
                tint = Color.White, // Sesuaikan warna ikon
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Button 2
        Button(
            onClick = { onButton2Click() },
            modifier = Modifier
                .width(30.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)  // Ganti warna background menjadi putih
                .padding(start = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.dp, Color.Black)  // Tambahkan border hitam
            )
            {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    tint = Color.White // Sesuaikan warna ikon
                )
            }
        }
        Button(
            onClick = { onBackPressed() }, // Pass onBackPressed here
            modifier = Modifier
                .width(30.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(3.dp, Color.Black)
                .padding(start = 2.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White // Sesuaikan warna ikon
            )
        }
    }
}
@Composable
@Preview(showBackground = true)
fun SearchScreenPreview() {
    Friendly_TravelTheme {
        SearchBar(
            onButton1Click = {
                // Aksi yang ingin dilakukan saat Button 1 diklik
            },
            onButton2Click = {
                // Aksi yang ingin dilakukan saat Button 2 diklik
            },
            onBackPressed = {

            }
        )
    }
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
        Row(modifier = Modifier.padding(8.dp)) {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = {},
                active = false,
                onActiveChange = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                placeholder = {
                    Text(stringResource(R.string.search))
                },
                shape = MaterialTheme.shapes.large,
                colors = SearchBarDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.background
                ), modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
            ){}

        }
    }
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SearchPreview(){
    val query = remember { mutableStateOf("") }
    val onQueryChange: (String) -> Unit = { query.value = it }

    Search(
        query = query.value,
        onQueryChange = onQueryChange,
    )
}