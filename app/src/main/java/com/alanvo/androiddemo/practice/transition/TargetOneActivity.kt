package com.alanvo.androiddemo.practice.transition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanvo.androiddemo.R
import com.alanvo.androiddemo.common.transition
import com.alanvo.androiddemo.databinding.ActivityTargetOneBinding

class TargetOneActivity : AppCompatActivity() {
    private var binding: ActivityTargetOneBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTargetOneBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupViews()
    }

    private fun setupViews() {
        binding?.apply {
            this@TargetOneActivity.transition(TransitionTargetActivity::class.java, imageView, "robot")
        }
    }
}