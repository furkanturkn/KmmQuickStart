package org.furkan.kmmquickstart.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.furkan.kmmquickstart.core.presentation.Slot

@Composable
fun RoundedSurface(
    modifier: Modifier = Modifier,
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    color: Color = MaterialTheme.colorScheme.surface,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    contentColor: Color = contentColorFor(color),
    content: Slot,
) {
    Surface(
        color = color,
        contentColor = contentColor,
        shape = shape,
        modifier = modifier,
        border = borderStroke
    ) {
        content()
    }
}
