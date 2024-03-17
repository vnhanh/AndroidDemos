package com.vnhanh.demo.feature.authentication.domain.validation

import android.util.Patterns

class AuthenticationFieldValidationUseCase() {
    private val passwordRegex =
        Regex("""^(?=.*[0-9]) (?=.*[a-z]) (?=.*[A-Z]) (?=.*[!@#$%^&*(){}|\[\]\\;:'"<>,.?/]) .{4,20}$""")

    fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean = passwordRegex matches password

    fun areEmailAndPasswordValid(email: String, password: String): Boolean =
        isEmailValid(email) && isPasswordValid(password)
}
