package com.clean.architecture.presentation.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.architecture.presentation.view.login.sign_in.GoogleAuthClient
import com.clean.architecture.presentation.view.login.sign_in.SignInResult
import com.clean.architecture.presentation.view.login.sign_in.SignInState
import com.clean.domain.User
import com.clean.domain.useCase.GetUserFromFireBaseUseCase
import com.clean.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserUseCase: GetUserFromFireBaseUseCase,
    val googleAuthClient: GoogleAuthClient
) :
    ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val _loginState = MutableStateFlow<Resource<User>>(Resource.Loading())
    val loginState = _loginState.asStateFlow()

    fun getUser(userId: String) {
        getUserUseCase(userId)
            .onStart {
                _loginState.emit(Resource.Loading(null))
            }
            .onEach { user ->
                _loginState.emit(Resource.Success(user))
            }.catch { e ->
                _loginState.emit(Resource.Error(e.message ?: ""))
            }.flowOn(Dispatchers.IO).launchIn(viewModelScope)

    }

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage

            )

        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}