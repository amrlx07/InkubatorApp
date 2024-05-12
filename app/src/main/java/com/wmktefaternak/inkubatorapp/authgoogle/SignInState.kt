package com.wmktefaternak.inkubatorapp.authgoogle

data class SignInState(
    val isSignSuccesful: Boolean = false,
    val signInError: String? = null
)
