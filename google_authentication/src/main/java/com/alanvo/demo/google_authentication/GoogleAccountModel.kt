package com.alanvo.demo.google_authentication

import android.accounts.Account
import android.net.Uri

data class GoogleAccountModel(
    val id: String?,
    val idToken: String?,
    val account: Account?,
    val email: String?,
    val displayName: String?,
    val familyName: String?,
    val givenName: String?,
    val photoUrl: Uri?,
    val isExpired: Boolean = true,
)