package com.clean.architecture.presentation.view.home

import androidx.lifecycle.ViewModel
import com.clean.architecture.presentation.view.home.sign_in.SignInResult
import com.clean.architecture.presentation.view.home.sign_in.SignInState
import com.clean.data.model.MovieDto
import com.clean.domain.useCase.GetUserListFromFireBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(getUserListFromFireBaseUseCase: GetUserListFromFireBaseUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()
    fun loginWithGoogle() {


    }

    fun onSignInResult(result: SignInResult) {
        _state.update {it.copy(
            isSignInSuccessful = result.data!=null,
            signInError = result.errorMessage

        )

        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }

//    private val _state = MutableStateFlow(
//        MovieDto(
//            movieId = 0,
//            overview = "",
//            pk = 0,
//            posterPath = "",
//            title = "",
//            rating = "",
//            releaseDate = ""
//        )
//    )
//    val state = _state.asStateFlow()

    init {
//        getUserListFromFireBaseUseCase().onStart { Resource.Loading(null) }
//            .onEach {
//                Log.d("ddd", it.toString())
//            }
//            .catch {
//                Resource.Error(message = it.message ?: "", data = null)
//            }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

}