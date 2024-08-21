package nextstep.payments.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import nextstep.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentsTopBar(
    title: String,
    modifier: Modifier = Modifier,
    titleTextAlign: TextAlign = TextAlign.Start,
    onBackClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null,
    actionContentDescription: String? = null,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                textAlign = titleTextAlign,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.top_bar_back_content_description),
                    )
                }
            }
        },
        actions = {
            if (onActionClick != null) {
                IconButton(onClick = { onActionClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = actionContentDescription,
                    )
                }
            }
        },
        modifier = modifier,
    )
}