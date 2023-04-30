package com.clean.domain.repository

import androidx.paging.PagingData
import com.clean.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun getMoviesFromDB(movieId: Int): Flow<Movie>

}