package com.alanvo.androiddemo.common

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.alanvo.androiddemo.practice.transition.TransitionTargetActivity


fun<T : AppCompatActivity> Activity.transition(targetClassJava: Class<T>, imageView: ImageView, sharedElementStr: String) {
    val intent = Intent(this, targetClassJava)
    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, imageView, sharedElementStr).toBundle())
}