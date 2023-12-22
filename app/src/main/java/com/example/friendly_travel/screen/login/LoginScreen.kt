package com.example.friendly_travel.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.friendly_travel.navigation.Screen
import com.example.friendly_travel.screen.register.RegisterScreen

@Composable
fun LoginScreen(navController: NavController) {
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

        // Pindahkan Button ke dalam Column dan atur modifier
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick= {  navController.navigate(Screen.Home.route) },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            ) {
                Text("Login")
            }
        }

        TextButton(
            onClick = { navController.navigate("register") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Don't have an account? Register")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginPreview(){
    LoginScreen(navController = rememberNavController())
}