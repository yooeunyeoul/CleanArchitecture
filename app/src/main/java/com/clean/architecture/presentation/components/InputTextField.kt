package com.clean.architecture.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clean.architecture.ui.theme.Gray600
import com.clean.architecture.ui.theme.Gray900
import com.clean.architecture.ui.theme.MovieTypography
import com.clean.architecture.ui.theme.Primary300
import com.clean.architecture.ui.theme.Purple
import com.clean.architecture.ui.theme.Purple80
import com.clean.architecture.ui.theme.SemanticNegative300
import com.clean.architecture.ui.theme.SemanticNegative500
import com.clean.architecture.ui.theme.SemanticPositive300
import com.clean.architecture.ui.theme.SemanticPositive500
import com.clean.architecture.ui.theme.pretendard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    text: String = "",
    placeHolder: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    errorStatus: Boolean = false,
    positiveStatus: Boolean = false,
    errorMessage: String? = null,
    successMessage: String? = null,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions? = null
) {
    var value by remember { mutableStateOf(text) }
    val focusBorderColor =
        if (positiveStatus) Purple80 else Purple80

    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    Column {
        OutlinedTextField(
            modifier = modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
                .background(shape = RoundedCornerShape(12.dp), color = Color.White),
            value = text,
            singleLine = true,
            isError = errorStatus,
            onValueChange = {
                if (it.length <= maxLength) value = it
                onValueChange(value)
            },
            placeholder = { Text(text = placeHolder, fontSize = 17.sp) },
            shape = RoundedCornerShape(12.dp),
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = focusBorderColor,
                unfocusedBorderColor = Purple80,
                placeholderColor = Gray600,
                focusedLabelColor = Primary300,
                unfocusedLabelColor = Gray600,
                errorBorderColor = SemanticNegative300,
                errorTrailingIconColor = Gray600,
                textColor = Color.Black
            ),
            keyboardOptions = keyboardOptions ?: KeyboardOptions(),
            textStyle = TextStyle(
                fontSize = 17.sp,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium
            )
        )

        if (errorStatus || positiveStatus) {
            Text(
                modifier = Modifier
                    .padding(top = 4.dp, start = 6.dp)
                    .width(236.dp),
                text = if (errorStatus) errorMessage ?: "" else successMessage ?: "",
                color = if (errorStatus) SemanticNegative500 else SemanticPositive500,
                style = MovieTypography.caption1,
            )
        }
    }
}

@Preview
@Composable
fun previewInputTextField() {
    InputTextField(placeHolder = "asdfdasf", onValueChange = {}, maxLength = 2)
}