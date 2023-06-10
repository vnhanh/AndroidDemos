package com.alanvo.androiddemo.features.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text


class FirstComposeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // must add dependency activity-compose
            MessageCard("Alan")
        }
    }

    @Composable
    fun MessageCard(name: String) {
        Text(text = "Hello $name")
    }
}