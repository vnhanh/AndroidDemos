package com.alanvo.androiddemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alanvo.androiddemo.databinding.ActivityMainBinding
import com.alanvo.demo.google_authentication.GoogleSignInHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val ggSignInHelper by lazy {
        GoogleSignInHelper(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupGoogleSignIn()
    }

    private fun setupViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupGoogleSignIn() {
        ggSignInHelper.setup()
    }

    override fun onStart() {
        super.onStart()
        updateUIRelyGoogleSignInAccount()
    }

    private fun updateUIRelyGoogleSignInAccount() {
        val ggAccount = ggSignInHelper.getLastAccount()

        if (ggAccount != null && !ggAccount.isExpired) {
            updateUIOnSignedInGoogle()
        } else {
            updateUIOnNotSignInGoogle()
        }
    }

    private fun updateUIOnSignedInGoogle() {
        binding.tvGoogleSigninState.text = "Signed In"
    }

    private fun updateUIOnNotSignInGoogle() {
        binding.tvGoogleSigninState.text = "Not sign in"
    }
}