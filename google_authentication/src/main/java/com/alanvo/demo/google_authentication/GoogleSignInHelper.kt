package com.alanvo.demo.google_authentication

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import java.lang.ref.WeakReference

class GoogleSignInHelper(val context: Context) {
    private val weakContext = WeakReference(context)
    private var gsc: GoogleSignInClient? = null

    fun setup() {
        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        gsc = weakContext.get()?.let { context ->
            GoogleSignIn.getClient(context, gso)
        }
    }

    fun getLastAccount(): GoogleAccountModel? {
        var account: GoogleAccountModel? = null
        val context: Context? = weakContext.get()

        if (context != null) {
            account = GoogleSignIn.getLastSignedInAccount(context)?.let { ggSignInAccount ->
                return@let GoogleAccountModel(
                    id = ggSignInAccount.id,
                    idToken = ggSignInAccount.idToken,
                    account = ggSignInAccount.account,
                    email = ggSignInAccount.email,
                    displayName = ggSignInAccount.displayName,
                    familyName = ggSignInAccount.familyName,
                    givenName = ggSignInAccount.givenName,
                    photoUrl = ggSignInAccount.photoUrl,
                    isExpired = ggSignInAccount.isExpired,
                )
            }
        }

        Log.d("log", "Alan - gg account: ${account}")

        return account
    }

    fun signIn(activity: ActivityResultLauncher<Intent>) {
        gsc?.signInIntent?.also { intent ->
            activity.launch(intent)
        }
    }

    fun handleGoogleSignInTask(data: Intent): Result<GoogleAccountModel> {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            Log.d("log", "Alan - got google account")
            return Result.success(
                GoogleAccountModel(
                id = account.id,
                idToken = account.idToken,
                email = account.email,
                displayName = account.displayName,
                familyName = account.familyName,
                givenName = account.givenName,
                isExpired = account.isExpired,
                photoUrl = account.photoUrl,
                account = account.account,
                ))
        } catch (e: Exception) {
            Log.e("log", "Alan - handleGoogleSignInTask() - exception ${e.message}")
            return Result.failure(e)
        }
    }

}