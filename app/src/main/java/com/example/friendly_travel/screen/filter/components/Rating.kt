package com.example.friendly_travel.screen.filter.components

import android.media.Rating
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.friendly_travel.R


@Composable
fun MenuRating(
    filterValue: Int,
    rangeValues: IntRange,
    onValueChange: (Int) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(R.string.rating),
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "(${stringResource(R.string.min)})",
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Rating(
            selectedValue = filterValue,
            rangeValues = rangeValues,
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun Rating(
    selectedValue: Int,
    rangeValues: IntRange,
    onValueChange: (Int) -> Unit
) {
    Row {
        for (value in rangeValues) {
            IconButton(
                modifier = Modifier.size(30.dp),
                onClick = { onValueChange(value) }
            ) {
                Image(
                    painter = painterResource(
                        if (value <= selectedValue) R.drawable.ic_star
                        else R.drawable.ic_unstar
                    ),
                    contentDescription = "Rating star",
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}