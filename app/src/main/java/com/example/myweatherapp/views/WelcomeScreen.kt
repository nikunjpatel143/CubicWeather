package com.example.myweatherapp.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.healper.SharedPreferencesHelper

@Composable
fun welcomeScreen(onClick: (numbersOfVehicle: String) -> Unit) {
    val sharedPreferencesHelper = SharedPreferencesHelper(LocalContext.current)
    val cityList = sharedPreferencesHelper.getArrayList()
    var textValue by remember { mutableStateOf("") }
    val context = LocalContext.current



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(30.dp, 0.dp, 30.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Welcome To Cubic Weather App",
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 40.dp, 0.dp, 0.dp),
            textAlign = TextAlign.Left,
            fontSize = 30.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            value = textValue,
            onValueChange = {
                textValue = it
            },
            label = {
                Text(
                    "Enter Your City", style = TextStyle(
                        color =
                        Color.LightGray
                    )
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        ElevatedButton(modifier = Modifier
            .height(75.dp)
            .width(150.dp)
            .padding(0.dp, 30.dp, 0.dp, 0.dp),
            onClick = {
                if (textValue.isEmpty()) {
                    Toast.makeText(context, "Enter valid city", Toast.LENGTH_LONG).show()
                } else {
                    onClick(textValue)
                }
            }
        ) {
            Text(text = "Click Me", fontSize = 20.sp)
        }

        Text(
            text = "Favorite Cities",
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 30.dp, 0.dp, 0.dp),
            textAlign = TextAlign.Left,
            fontSize = 30.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        LazyColumn {
            items(cityList as List<String>) { item ->
                Box(modifier = Modifier
                    .height(50.dp)
                    .clickable {
                        onClick(item)

                    }) {
                    Column {
                        Text(
                            text = "${item}",
                            modifier = Modifier
                                .fillMaxWidth(), textAlign = TextAlign.Left,
                            fontSize = 20.sp,
                            color = Color.Black
                        )

                    }
                }
                Divider(color = Color.LightGray, thickness = 1.dp)

            }
        }


    }
}



@Composable
@Preview(showBackground = true)
fun previewWelcomeScreen() {
    // welcomeScreen(fun : ()->Unit)
}