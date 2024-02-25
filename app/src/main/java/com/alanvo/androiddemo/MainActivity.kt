package com.alanvo.androiddemo

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.alanvo.androiddemo.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
    }

}