package com.vnhanh.demo.feature.authentication.domain.validation

import android.util.Patterns
import com.google.gson.Gson

class AuthenticationFieldValidationUseCase(
    private val gson: Gson,
) {
    private val passwordRegex =
        Regex("""^(?=.*[0-9]) (?=.*[a-z]) (?=.*[A-Z]) (?=.*[!@#$%^&*(){}|\[\]\\;:'"<>,.?/]) .{4,20}$""")

    fun isEmailValid(email: String): Boolean =
        email.isBlank() || Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean {
        return passwordRegex matches password
    }
}
