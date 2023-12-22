package com.example.friendly_travel.components

import com.example.friendly_travel.R

data class Menu(
    val image: Int,
    val title: String,
    val price: String,
)

val dummyMenu = listOf(
    Menu(R.drawable.menu1, "travel app1", "Rp 28.000"),
    Menu(R.drawable.menu2, "travel app2", "Rp 18.000"),
    Menu(R.drawable.menu3, "travel app3", "Rp 20.000"),
    Menu(R.drawable.menu4, "travel app4", "Rp 16.000"),
)

val dummyBestSellerMenu = dummyMenu.shuffled()