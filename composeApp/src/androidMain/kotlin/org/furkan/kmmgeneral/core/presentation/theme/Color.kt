package org.furkan.kmmquickstart.core.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import core.presentation.Colors

val DarkGray = Color(Colors.DarkGray)
val MediumGray = Color(Colors.MediumGray)
val LightGray = Color(Colors.LightGray)
val SurfaceColor = Color(Colors.SurfaceColor)
val OnSurfaceColor = Color(Colors.OnSurfaceColor)
val TextWhite = Color(Colors.TextWhite)
val PrimaryColor = Color(Colors.PrimaryColor)
val FaintTextColor = Color(Colors.FaintTextColor)
val ErrorColor = Color(Colors.ErrorColor)
val TextFieldBorderColor = Color(Colors.TextFieldBorderColor)
val TextFieldHintColor = Color(Colors.TextFieldHintColor)
val TextFieldErrorBackgroundColor = Color(Colors.TextFieldErrorBackgroundColor)


val lightColors = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = SurfaceColor,
    onSurface = OnSurfaceColor
)

val darkColors = darkColorScheme(
    primary = PrimaryColor,
    background = DarkGray,
    onBackground = TextWhite,
    onPrimary = DarkGray,
    surface = MediumGray,
    onSurface = LightGray
)