package com.example.friendly_travel.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.friendly_travel.R
import com.example.friendly_travel.ui.theme.Friendly_TravelTheme

@Composable
fun RegisterScreen(navController: NavController) {
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
        )
        Spacer(modifier = Modifier.padding(5.dp))
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
        )
        Spacer(modifier = Modifier.padding(5.dp))
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp)),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(5.dp))

        Button(
            onClick = {
                //if (passwordState.value == confirmPasswordState.value) {
                    // Implementasi pendaftaran di sini
                //    navController.navigate(Screen.Login.route)
                //} else {
                    // Tampilkan notifikasi jika password dan konfirmasi password tidak cocok
               // }
            },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(vertical = 16.dp)
        ) {
            Text("Register")
        }

        TextButton(
            onClick = {// navController.navigate(Screen.Login.route)
                 },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? Login")

        }
    }
}

@Preview(showBackground = true)
@Composable
    fun RegisterScreenPreview() {
        Friendly_TravelTheme {
            RegisterScreen(navController = rememberNavController())
        }
    }