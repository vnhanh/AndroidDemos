package com.alanvo.androiddemo.practice.transition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.TransitionInflater
import android.view.Window
import com.alanvo.androiddemo.R
import com.alanvo.androiddemo.databinding.ActivityTransitionTargetBinding

class TransitionTargetActivity : AppCompatActivity() {
    private var binding: ActivityTransitionTargetBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            exitTransition = Explode()
        }
        super.onCreate(savedInstanceState)
        binding = ActivityTransitionTargetBinding.inflate(layoutInflater)
//        binding?.apply {
//            this.imageView.transitionName = "robot"
//        }
        setContentView(binding?.root)
    }
}