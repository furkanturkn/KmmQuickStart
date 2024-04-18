package org.furkan.kmmquickstart.core.presentation.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.furkan.kmmquickstart.core.presentation.theme.AppTheme


@Composable
fun LoadingAnimation() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()

            .pointerInput(Unit) { detectTapGestures { } }, // Add this line to disable click events
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.size(size = 80.dp),
            shadowElevation = 8.dp
        ) {
            Box(modifier = Modifier.padding(20.dp)) {
                LoadingAnimationContent()
            }
        }
    }
}

@Composable
private fun LoadingAnimationContent() {
    CircularProgressIndicator()
}

@Composable
@Preview
fun LoadingAnimationPreview() {
    AppTheme {
        LoadingAnimation()
    }
}