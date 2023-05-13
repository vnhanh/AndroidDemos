package com.alanvo.androiddemo

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.alanvo.androiddemo.databinding.ActivityMainBinding
import com.alanvo.demo.google_authentication.GoogleAccountModel
import com.alanvo.demo.google_authentication.GoogleSignInHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class MainActivity : AppCompatActivity(), View.OnClickListener {
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
        setupSignInButton(signInButton = binding.btnGoogleSignin)
    }

    private fun setupSignInButton(signInButton: SignInButton) {
        setTextForSignInButton(signInButton)
        signInButton.setColorScheme(SignInButton.COLOR_DARK)
        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener(this)
    }

    private fun setTextForSignInButton(signInButton: SignInButton) {
        for (i in 0 until signInButton.childCount) {
            val child = signInButton.getChildAt(i)
            if (child is TextView) {
                child.text = "Sign in with Google"
                return
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.btnGoogleSignin.id -> signIn()

            else -> {}
        }
    }

    private val googleSignIntResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.also { data ->
                handleGoogleSignInResult(data)
            }
        }
    }

    private fun handleGoogleSignInResult(data: Intent) {
        ggSignInHelper.handleGoogleSignInTask(data)
            .onSuccess { account: GoogleAccountModel ->
                binding.tvGoogleSigninState.text = "Signed In with email " + account.email
            }
            .onFailure {
                binding.tvGoogleSigninState.text = "Signed In failed"
            }
    }

    private fun signIn() {
        ggSignInHelper.signIn(googleSignIntResult)
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