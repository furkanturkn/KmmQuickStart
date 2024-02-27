package core.util

sealed class AuthError {
    data object FieldBlank : AuthError()
    data object InvalidPhoneNumber : AuthError()
    data object InvalidEmail : AuthError()
}

