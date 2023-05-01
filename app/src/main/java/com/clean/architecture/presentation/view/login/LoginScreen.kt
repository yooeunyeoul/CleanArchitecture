package com.clean.architecture.presentation.view.login

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.clean.architecture.R
import com.clean.architecture.presentation.navigation.Screen
import com.clean.architecture.presentation.view.login.components.LoginButton
import com.clean.architecture.ui.theme.Black01
import com.clean.architecture.ui.theme.MovieTypography
import com.clean.architecture.ui.theme.Purple01
import com.clean.data.model.MovieDto
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, homeViewModel: LoginViewModel) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by homeViewModel.state.collectAsStateWithLifecycle()
    val loginState by homeViewModel.loginState.collectAsStateWithLifecycle()
    val googleAuthUiClient = homeViewModel.googleAuthClient

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                coroutineScope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    homeViewModel.onSignInResult(signInResult)
                }
            }
        }
    )

    LaunchedEffect(key1 = Unit) {
        if (googleAuthUiClient.getSignedInUser() != null) {
            Toast.makeText(context, "Auto  Login Successful", Toast.LENGTH_LONG).show()
            homeViewModel.getUser(googleAuthUiClient.getSignedInUser()?.userId ?: "")
        }
    }


    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()

        }
    }
    LaunchedEffect(key1 = loginState) {
        if (loginState.data != null) {
            when {
                loginState.data?.userName == null -> {
                    navController.navigate(Screen.NickName.route)
                }

                loginState.data?.gender == null -> {
                    navController.navigate(Screen.SettingGender.route)
                }
            }
        }
    }
    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            Toast.makeText(context, "Sign in Successful", Toast.LENGTH_LONG).show()
            homeViewModel.getUser(googleAuthUiClient.getSignedInUser()?.userId ?: "")
            homeViewModel.resetState()
        }
    }
    HomeScreen {
        coroutineScope.launch {
            val signInIntentSender = googleAuthUiClient.signIn()
            launcher.launch(
                IntentSenderRequest.Builder(
                    signInIntentSender ?: return@launch
                ).build()
            )

        }

    }


}

@Composable
private fun HomeScreen(onClickLogin: () -> Unit) {

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.main),
                contentDescription = null,
                modifier = Modifier
                    .widthIn(0.dp, max = 500.dp)
                    .padding(start = 40.dp, end = 40.dp),
                contentScale = ContentScale.FillWidth
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 40.dp, end = 40.dp, bottom = 90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "주변의 새로운 친구를 \n만나러 가볼까요?",
                style = MovieTypography.h1,
                color = Black01,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            LoginButton(
                text = "구글 로그인",
                icon = painterResource(id = R.drawable.icon_google),
                backgroundColor = Purple01.copy(alpha = 0.2f),
                textColor = Color.Black
            ) {
                onClickLogin()
            }

        }

    }


}


@Preview(device = "id:pixel_5", showBackground = true)
@Composable
fun previewHomeScreen(
    @PreviewParameter(provider = MoviePreviewParameterProvider::class, limit = 2) movie: MovieDto
) {
    HomeScreen {

    }
}

class MoviePreviewParameterProvider : PreviewParameterProvider<MovieDto> {
    override val values: Sequence<MovieDto> = sequenceOf(
        MovieDto(
            pk = 0,
            movieId = 1,
            overview = "",
            posterPath = "",
            title = "ㅁㄴㅇㄹ",
            rating = "ㅁㄴㅇㄹ",
            releaseDate = null
        )
    )
}