package com.example.friendly_travel.screen.category

data class Category(
val id: Int,
val name: String,
val category:String,
val alamat: String,
val photo: Int,
val deskripsi : String,
val rating : Double,
val harga: Double,
val isFavorite : Boolean = false
)