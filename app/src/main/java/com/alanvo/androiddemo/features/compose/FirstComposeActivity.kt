package com.alanvo.androiddemo.features.compose

import Message
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview


class FirstComposeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // must add dependency activity-compose
            PreviewMessageCard()
        }
    }
    @Composable
    fun MessageCard(msg: Message) {
        Column {
            Text(msg.author)
            Text(msg.body)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewMessageCard() {
        MessageCard(Message("Alan", "500 code practices"))
    }
}