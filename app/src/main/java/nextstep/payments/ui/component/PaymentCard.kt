package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.Brand
import nextstep.payments.model.Card
import nextstep.payments.model.toColor
import nextstep.payments.model.toIcon
import nextstep.payments.model.toName
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    brand: Brand = Brand.NONE,
    cardNumber: String = "",
    expiredDate: String = "",
    ownerName: String = "",
    cardNumberVisualTransformation: CardNumberVisualTransformation = CardNumberVisualTransformation.ASTERISK_MASKED,
    expiredDateVisualTransformation: ExpiredDateVisualTransformation = ExpiredDateVisualTransformation.DEFAULT,
    onClick: () -> Unit = {},
) {
    val transformedCardNumber =
        remember(cardNumber) {
            cardNumberVisualTransformation.filter(AnnotatedString(cardNumber)).text
        }
    val transformedExpiredDate =
        remember(expiredDate) {
            expiredDateVisualTransformation.filter(AnnotatedString(expiredDate)).text
        }

    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .shadow(8.dp)
                .height(124.dp)
                .widthIn(min = 0.dp, max = 208.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = brand.toColor())
                .clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
        ) {
            Text(
                text = brand.toName(),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                modifier = Modifier.weight(1f).testTag("cardBrand"),
            )

            Box(
                modifier =
                    Modifier
                        .padding(top = 15.dp, bottom = 8.dp)
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        ),
            )

            Text(
                text = transformedCardNumber,
                color = Color.White,
                style =
                    MaterialTheme.typography.labelMedium.copy(
                        letterSpacing = 1.sp,
                    ),
                lineHeight = 14.sp,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("cardNumber"),
            )
            Row {
                Text(
                    text = ownerName,
                    style = MaterialTheme.typography.labelSmall,
                    lineHeight = 14.sp,
                    color = Color.White,
                    modifier = Modifier.weight(1f).testTag("ownerName"),
                )
                Text(
                    text = transformedExpiredDate,
                    style = MaterialTheme.typography.labelSmall,
                    lineHeight = 14.sp,
                    color = Color.White,
                    modifier = Modifier.testTag("expiredDate"),
                )
            }
        }

        if (brand != Brand.NONE) {
            Icon(
                painter = brand.toIcon(),
                contentDescription = brand.toName(),
                tint = Color.White,
                modifier =
                    Modifier
                        .padding(14.dp)
                        .size(24.dp)
                        .align(Alignment.TopEnd),
            )
        }
    }
}

@Preview
@Composable
private fun PaymentCardPreview(
    @PreviewParameter(PaymentCardPreviewProvider::class) card: Card,
) {
    PaymentsTheme {
        PaymentCard(
            brand = card.brand,
            cardNumber = card.cardNumber,
            expiredDate = card.expiredDate,
            ownerName = card.ownerName,
            onClick = {},
        )
    }
}

class PaymentCardPreviewProvider :
    CollectionPreviewParameterProvider<Card>(
        collection =
            Brand.entries
                .drop(1)
                .map {
                    Card(
                        brand = it,
                        cardNumber = "1234567890123456",
                        expiredDate = "1234",
                        ownerName = "홍길동",
                        password = "1234",
                    )
                },
    )
