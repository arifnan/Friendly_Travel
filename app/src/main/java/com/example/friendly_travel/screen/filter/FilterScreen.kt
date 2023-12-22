package com.example.friendly_travel.screen.filter

import android.icu.text.NumberFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.friendly_travel.screen.filter.components.DropDownMenuLocation
import com.example.friendly_travel.screen.filter.components.MenuRating
import java.util.Locale

@Composable
fun FilterScreen(
    ratingValues: (IntRange) ,
    ratingFilter: (Int),
    onRatingChange: (Int) -> Unit,
    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit,
    onButton3Click: () -> Unit,
    onButton4Click: () -> Unit,
    onApplyClick: () -> Unit,
    ) {
    var price by remember { mutableStateOf(10000f..1000000f) }
    var appliedFilters by remember { mutableStateOf(false) }
    var maxRange by remember { mutableStateOf(1000000f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = "Filter ", fontSize = 30.sp, modifier = Modifier
                .heightIn(40.dp)
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "Categories", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Row() {

                    OutlinedButton(onClick = onButton1Click) {
                        Text(text = "All")
                    }

                    Spacer(modifier = Modifier.width(6.dp))


                    OutlinedButton(onClick = onButton2Click) {
                        Text(text = "Mountains")
                    }
                    Spacer(modifier = Modifier.width(6.dp))


                    OutlinedButton(onClick = onButton3Click) {
                        Text(text = "Beach")
                    }
                    Spacer(modifier = Modifier.width(6.dp))


                    OutlinedButton(onClick = onButton4Click) {
                        Text(text = "Sea")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Price Range", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text("Rp ${NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(price.start)}", fontSize = 22.sp)
                    }

                    RangeSlider(
                        value = price,
                        onValueChange = { price = it },
                        valueRange = 10000f..1000000f
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Location", fontSize = 20.sp)
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    DropDownMenuLocation()
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Ratings", fontSize = 20.sp)
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    MenuRating(
                        filterValue = ratingFilter,
                        rangeValues = ratingValues,
                        onValueChange = onRatingChange
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                onApplyClick() // Apply the filters when the button is clicked
                appliedFilters = true // Update the applied filters state
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally),
            enabled = !appliedFilters // Disable the button if the filters are already applied
        ) {
            Text(text = "Apply", fontSize = 20.sp)
        }
    }
}


/**
@Preview(showBackground = true)
@Composable
fun FilterPreview(){
    FilterScreen(
        onButton2Click = {},
        onButton1Click = {},
        onButton3Click ={},
        onButton4Click ={},
        onApplyClick = {} ,
        onRatingChange = {},
        ratingFilter = {},
        ratingValues = {}

    )
}*/