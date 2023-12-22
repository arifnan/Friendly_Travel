package com.example.friendly_travel.screen

import android.os.Process
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.friendly_travel.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onExit: () -> Unit = {
    Process.killProcess(Process.myPid())
}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: handle back navigation */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Gambar user
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "User profile picture",
                    modifier = Modifier.size(150.dp)
                )

                // Tombol edit profile
                Button(
                    onClick = { /* TODO: handle edit profile navigation */ },
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Text("Edit Profile")
                }

                // Tombol keluar
                Button(
                    onClick = { onExit() },
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Text("Exit")
                }
            }
        }
    )
}