package com.alanvo.androiddemo.features.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class FirstComposeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // must add dependency activity-compose

        }
    }
}