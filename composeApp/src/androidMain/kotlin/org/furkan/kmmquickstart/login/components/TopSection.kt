package org.furkan.kmmquickstart.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.furkan.kmmquickstart.R
import org.furkan.kmmquickstart.SharedRes
import org.furkan.kmmquickstart.core.presentation.components.sharedStringResource

@Composable
fun TopSection() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(
                    id = org.furkan.kmmquickstart.shared.R.drawable.test
                ),
                contentDescription = sharedStringResource(id = SharedRes.strings.app_logo_alt),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(
                text = sharedStringResource(id = SharedRes.strings.app_name),
                color = MaterialTheme.colorScheme.background,
                style = typography.displayLarge
            )


            Text(
                text = sharedStringResource(id = SharedRes.strings.login_description),
                color = MaterialTheme.colorScheme.background,
                style = typography.headlineLarge
            )

        }

    }

}

@Composable
@Preview
fun TopSectionPreview() {
    TopSection()
}