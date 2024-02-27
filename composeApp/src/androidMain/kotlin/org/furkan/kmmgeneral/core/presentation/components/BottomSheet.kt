package org.furkan.kmmquickstart.core.presentation.components

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import org.furkan.kmmquickstart.core.presentation.Slot


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    skipPartiallyExpanded: Boolean = true,
    onDismiss: () -> Unit,
    content: Slot,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        content()
    }
}