package com.clean.architecture.presentation.view.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.clean.data.model.MovieDto

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()
    
    HomeScreen(movie = state)


}

@Composable
private fun HomeScreen(movie: MovieDto) {

    Text(text = "this is in app")


}


@Preview(device = "id:pixel_5", showBackground = true)
@Composable
fun previewHomeScreen(
    @PreviewParameter(provider = MoviePreviewParameterProvider::class, limit = 2) movie: MovieDto
) {
    HomeScreen(movie = movie)
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
        ),
        MovieDto(
            pk = 0,
            movieId = 1,
            overview = "",
            posterPath = "",
            title = "ㅁㄴㄹㅁㄴㅇㄹ",
            rating = "ㅁㄴㄹㅇㅁ",
            releaseDate = null
        )
    )
}