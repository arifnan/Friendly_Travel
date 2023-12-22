package com.example.friendly_travel.screen.filter.components

import android.icu.text.NumberFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun PriceSlider(
    value: MutableState<Int>,
    onValueChange: (Int) -> Unit,
    min: Int,
    max: Int
) {
    Column {
        Text(NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(value.value), fontSize = 22.sp)
        Slider(
            value = value.value.toFloat(),
            onValueChange = { newValue ->
                if (newValue >= min && newValue <= max) {
                    onValueChange(newValue.toInt())
                }
            },
            valueRange = min.toFloat()..max.toFloat()
        )
    }
}

