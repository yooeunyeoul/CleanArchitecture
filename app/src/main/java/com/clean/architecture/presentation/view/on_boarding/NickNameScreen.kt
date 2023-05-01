package com.clean.architecture.presentation.view.on_boarding

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.clean.architecture.presentation.components.BottomButton
import com.clean.architecture.presentation.components.InputTextField
import com.clean.architecture.presentation.navigation.Screen
import com.clean.architecture.ui.theme.MovieTypography
import com.clean.architecture.ui.theme.Purple
import com.clean.architecture.ui.theme.Purple01
import com.clean.domain.util.Resource

@Composable
fun NickNameScreen(
    navController: NavController,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by onBoardingViewModel.state.collectAsStateWithLifecycle()
    NickNameScreen { inputName ->
        onBoardingViewModel.settingNickName(inputName)
    }
    LaunchedEffect(key1 = state) {
        when (state) {
            is Resource.Success -> {
                navController.navigate(Screen.SettingGender.route)
            }
            is Resource.Error -> Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            is Resource.Loading -> {

            }
        }
    }

}

@Composable
private fun NickNameScreen(onClickNext: (String) -> Unit) {
    var inputName by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Purple01.copy(alpha = 0.1f))
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "이름이 뭔가요?", style = MovieTypography.h1)
            Spacer(modifier = Modifier.height(32.dp))
            InputTextField(
                text = inputName,
                placeHolder = "",
                onValueChange = { inputText ->
                    inputName = inputText
                },
                maxLength = 10,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        BottomButton(
            text = "다음",
            icon = null,
            modifier = Modifier
                .padding(bottom = 40.dp),
            backgroundColor = Purple,
            textColor = Color.White
        ) {
            onClickNext(inputName)
        }
    }
}

@Preview
@Composable
fun previewNickNameScreen() {
    MaterialTheme() {
        NickNameScreen {}
    }

}