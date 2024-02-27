package login.domain.use_case

import core.util.AuthError

class ValidatePhoneNumberUseCase {

    operator fun invoke(phoneNumber: String): AuthError? {
        if (phoneNumber.isBlank()) {
            return AuthError.FieldBlank
        }

        if (!phoneNumber.all { it.isDigit() }) {
            val emailRegex = Regex("^\\S+@\\S+\\.\\S+\$")
            return emailRegex.matches(phoneNumber).let { isValid ->
                if (isValid) null else AuthError.InvalidEmail
            }
        }

        if (phoneNumber.length < 7) {
            return AuthError.InvalidPhoneNumber
        }

        return null
    }
}