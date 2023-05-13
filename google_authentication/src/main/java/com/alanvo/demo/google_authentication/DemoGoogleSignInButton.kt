package com.alanvo.demo.google_authentication

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import com.google.android.gms.common.SignInButton

class DemoGoogleSignInButton: FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        val signInButton = SignInButton(context)
        setBackground(signInButton)
        addView(signInButton)
    }

    private fun setBackground(signInButton: SignInButton) {
        for (i in 0 until signInButton.childCount) {
            val child = signInButton.getChildAt(i)
            Log.d("log", "Alan - Custom SignInButton - index ${i} - child view ${child.javaClass.simpleName}")
//            if (child is FrameLayout) {
//                child.setBackgroundResource(R.drawable.btn_radius_8)
//            }
        }
    }
}