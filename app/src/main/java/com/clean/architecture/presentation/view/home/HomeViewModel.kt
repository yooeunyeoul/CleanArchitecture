package com.clean.architecture.presentation.view.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.architecture.util.Resource
import com.clean.data.model.MovieDto
import com.clean.domain.useCase.GetUserListFromFireBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(getUserListFromFireBaseUseCase: GetUserListFromFireBaseUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(
        MovieDto(
            movieId = 0,
            overview = "",
            pk = 0,
            posterPath = "",
            title = "",
            rating = "",
            releaseDate = ""
        )
    )
    val state = _state.asStateFlow()

    init {
        getUserListFromFireBaseUseCase().onStart { Resource.Loading(null) }
            .onEach {
                Log.d("ddd", it.toString())
            }
            .catch {
                Resource.Error(message = it.message ?: "", data = null)
            }.launchIn(viewModelScope)
    }

}