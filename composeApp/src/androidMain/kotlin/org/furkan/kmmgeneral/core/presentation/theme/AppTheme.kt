package org.furkan.kmmquickstart.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.furkan.kmmquickstart.R
import org.furkan.kmmquickstart.SharedRes

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        lightColors
    } else {
        lightColors
    }

    val robotoFontFamily = FontFamily(
        Font(
            resId = R.font.roboto_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resId = R.font.roboto_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.roboto_bold,
            weight = FontWeight.Bold
        ),
    )

    val typography = Typography(
        displayLarge = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        ),
        displayMedium = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        displaySmall = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
    )

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}