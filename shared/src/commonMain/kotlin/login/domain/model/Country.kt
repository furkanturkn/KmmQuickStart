package login.domain.model


data class Country(
    val id: String,
    val name: String,
    val phoneCode: String,
    val locale: String?,
) {
    private val countryCodeToEmojiMap = mapOf(
        "TR" to "🇹🇷",
        "US" to "🇺🇸",
        "GB" to "🇬🇧",
    )

    fun toFlagEmoji(): String {
        val countryCode = locale!!.takeLast(2).uppercase()
        return countryCodeToEmojiMap[countryCode] ?: "️"
    }

    fun hasText(text: String): Boolean {
        val textLowerCased = text.lowercase()

        return name.lowercase().contains(textLowerCased) || phoneCode.lowercase()
            .contains(textLowerCased)
    }
}

val countryTurkey = Country(
    id = "tur",
    name = "Turkey",
    phoneCode = "90",
    locale = "tr-TR",
)

val countryUSA = Country(
    id = "usa",
    name = "Usa",
    phoneCode = "40",
    locale = "us-US",
)
