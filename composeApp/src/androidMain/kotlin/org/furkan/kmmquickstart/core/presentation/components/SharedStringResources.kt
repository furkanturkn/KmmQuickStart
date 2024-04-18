package org.furkan.kmmquickstart.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import core.util.Strings
import dev.icerock.moko.resources.StringResource

@Composable
fun sharedStringResource(id: StringResource, vararg args: Any): String {
    return Strings(LocalContext.current).get(id, args.toList())
}