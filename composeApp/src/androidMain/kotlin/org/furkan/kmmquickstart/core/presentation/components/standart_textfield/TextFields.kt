package org.furkan.kmmquickstart.core.presentation.components.standart_textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.furkan.kmmquickstart.core.presentation.Slot
import org.furkan.kmmquickstart.core.presentation.components.RoundedSurface
import org.furkan.kmmquickstart.core.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    textFieldState: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    containerModifier: Modifier = Modifier,
    labelText: String = "",
    hint: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: Slot? = null,
    trailingIcon: Slot? = null,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    isEditable: Boolean = true,
    maxLength: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    hasErrorOccurred: Boolean = false,
    errorMessage: String? = null,
    onDone: (() -> Unit)? = null,
    enabled: Boolean = true,
    requestFocus: Boolean = false
) {

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = requestFocus) {
        if (requestFocus) {
            focusRequester.requestFocus()
        }
    }

    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    var textFieldStyleState by remember {
        mutableStateOf(
            if (hasErrorOccurred) {
                TextFieldStyle(
                    borderColor = ErrorColor,
                    backgroundColor = TextFieldErrorBackgroundColor
                )
            } else {
                TextFieldStyle()
            }
        )
    }

    Column(modifier = containerModifier) {
        RoundedSurface(
            borderStroke = BorderStroke(
                width = 1.dp,
                color = textFieldStyleState.borderColor
            ),
            color = textFieldStyleState.backgroundColor,
            modifier = Modifier.height(60.dp)
        ) {
            Box {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedLabelColor = TextFieldHintColor,
                        containerColor = textFieldStyleState.backgroundColor,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    value = textFieldState,
                    onValueChange = {
                        if (isEditable && it.length <= maxLength) {
                            onValueChange(it)
                        }

                        textFieldStyleState = if (it.isNotEmpty() && isFocused) {
                            TextFieldStyle(borderColor = TextFieldBorderColor)
                        } else {
                            TextFieldStyle()
                        }
                    },
                    placeholder = { Text(hint, color = TextFieldHintColor) },
                    label = {
                        Text(
                            labelText,
                            color = TextFieldHintColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    visualTransformation = visualTransformation,
                    maxLines = maxLines,
                    singleLine = singleLine,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onDone?.invoke()
                        }
                    ),
                    enabled = enabled,
                    modifier = modifier
                        .fillMaxWidth()
                        .onFocusEvent {
                            isFocused = it.isFocused

                            textFieldStyleState = if (it.isFocused) {
                                TextFieldStyle(borderColor = TextFieldBorderColor)
                            } else {
                                TextFieldStyle(borderColor = textFieldStyleState.backgroundColor)
                            }
                        }
                        .align(Alignment.CenterStart)
                        .focusRequester(focusRequester)
                )

                /*
                if (isMandatory && textFieldState.isEmpty()) {
                    Text(
                        text = sharedStringResource(id = SharedRes.strings..strings.mandatory),
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .align(Alignment.CenterEnd)
                    )
                }

                 */
            }
        }

        if (hasErrorOccurred && errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = errorMessage, fontSize = 12.sp, color = MaterialTheme.colorScheme.error)
        }
    }
}


@Preview
@Composable
fun TextFieldPreview() {
    AppTheme {
        CustomTextField(
            textFieldState = "Furkan",
            onValueChange = {},
            labelText = "Username",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Face,
                    contentDescription = ""
                )
            }
        )
    }
}
