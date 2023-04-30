package com.clean.architecture.presentation.view.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.clean.architecture.R
import com.clean.architecture.ui.theme.Gray300
import com.clean.architecture.ui.theme.Gray700
import com.clean.architecture.ui.theme.MovieTypography

@Composable
fun LoginButton(
    text: String,
    icon: Painter?,
    textColor: Color = Gray700,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp,
    backgroundColor: Color = Color.Transparent,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
            .clip(RoundedCornerShape(32.dp))
            .border(borderWidth, borderColor, RoundedCornerShape(32.dp))
            .background(backgroundColor)
            .clickable {
                onClick()
            },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(start = 8.dp)
                .background(color = Color.White, shape = CircleShape).size(36.dp).align(Alignment.CenterStart)
        ) {
            icon?.let {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = it,
                    contentDescription = null
                )
            }
        }
        Text(
            text = text,
            color = textColor,
            style = MovieTypography.body6,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun AppleLoginButtonPreview() {
    Surface {
        LoginButton(
            text = "Apple 계정으로 계속하기",
            icon = painterResource(id = R.drawable.icon_google),
            textColor = Gray700,
            borderColor = Gray300
        ) {

        }
    }

}