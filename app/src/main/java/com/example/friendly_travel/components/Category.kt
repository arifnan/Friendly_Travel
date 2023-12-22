package com.example.friendly_travel.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.friendly_travel.R

data class Category(
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int
)

val dummyCategory = listOf(
    R.drawable.category1 to R.string.category_mountain,
    R.drawable.categor2 to R.string.category_beach,
    R.drawable.categor3 to R.string.category_culture,
    R.drawable.categor4 to R.string.category_cave,

    ).map { Category(it.first, it.second) }

