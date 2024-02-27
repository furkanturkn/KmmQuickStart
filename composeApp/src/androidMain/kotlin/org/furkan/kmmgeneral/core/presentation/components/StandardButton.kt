package org.furkan.kmmquickstart.core.presentation.components.standart_button


import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.furkan.kmmquickstart.R
import org.furkan.kmmquickstart.SharedRes

@Composable
fun StandardButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes resId: Int? = null,
    text: String? = null,
    textColor: Color = Color.White,
    buttonColor: ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
    enabled: Boolean = true,
    content: @Composable (() -> Unit)? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = buttonColor
    ) {

        content?.let {
            it()
        }

        resId?.let {
            Text(
                text = stringResource(id = resId),
                color = textColor
            )
        }
        text?.let {
            Text(
                text = text,
                color = textColor
            )
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    StandardButton(
        onClick = {},
        resId = org.furkan.kmmquickstart.shared.R.string.login,
    )
}


