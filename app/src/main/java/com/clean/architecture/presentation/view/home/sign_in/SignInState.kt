package com.clean.architecture.presentation.view.home.sign_in

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
) {
}