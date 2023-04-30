package com.clean.architecture.presentation.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.clean.architecture.R
import com.clean.architecture.presentation.view.home.components.LoginButton
import com.clean.architecture.ui.theme.Black01
import com.clean.architecture.ui.theme.MovieTypography
import com.clean.architecture.ui.theme.Purple01
import com.clean.data.model.MovieDto

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    HomeScreen {
        homeViewModel.loginWithGoogle()
    }


}

@Composable
private fun HomeScreen(onClickLogin: () -> Unit) {

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.main),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
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