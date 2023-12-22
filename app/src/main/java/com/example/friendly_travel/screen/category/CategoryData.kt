package com.example.friendly_travel.screen.category

import com.example.friendly_travel.R
import com.example.friendly_travel.model.Wisata

object CategoryData {
    val category = listOf(
        Category(
            id=0,
            name = "Pura Tanah Lot",
            category = "Budaya",
            alamat = " ",
            photo = R.drawable.tanahlot,
            deskripsi =  "Pura Tanah Lot adalah sbuah ikon dari wisata Bali yang wajib dikunjungi ketika Anda pelesir ke sana. Pura ini terdiri dari dua bangunan yang terpisah oleh lautan. Bangunan pertama terletak di bongkahan batu besar dan bangunan yang kedua berada di tebing. Pengunjung yang datang ke sini bisa menyeberang ke pura di tengah laut saat air sedang surut." ,
            rating =  8.6,
            harga = 30.0000,

            ),
        Category(
            id=2,
            name = "Pura Besakih",
            category = "Budaya",
            alamat = " ",
            photo = R.drawable.urabesaikh,
            deskripsi =  "Pura Besakih adalah salah satu pura untuk tempat peribadatan umat Hindu di Bali. Pura ini dikenal paling besar dan sakral. Banyak upacara keagamaan besar diadakan di sini setiap tahunnya.",
            rating =  8.6,
            harga = 30.0000,
        ),
        Category(
            id=4,
            name = "Pura Tirta Empul",
            category = "Budaya",
            alamat = " ",
            photo = R.drawable.empul4,
            deskripsi =  "Pura Tirta Empul adalah nama sebuah pura yang terletak di desa Manukaya, kecamatan Tampak Siring, kabupaten Gianyar. Keunikan arsitektur dan adanya mata air pada area dalam pura, membuat pura Tirta Empul menarik banyak kunjungan wisatawan, baik wisatawan mancanegara maupun wisatawan domestik. Jarak Tirta Empul dari airport Ngurah Rai, kurang lebih 52 kilometer yang memerlukan waktu 1 jam 30 menit.",
            rating =  8.8,
            harga = 15.0000,
        )
    )
}