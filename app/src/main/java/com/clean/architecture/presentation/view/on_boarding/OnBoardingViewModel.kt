package com.clean.architecture.presentation.view.on_boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.architecture.presentation.view.login.sign_in.GoogleAuthClient
import com.clean.domain.User
import com.clean.domain.useCase.UpdateUserToFireBaseUseCase
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
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserToFireBaseUseCase,
    val googleAuthClient: GoogleAuthClient
) :
    ViewModel() {

    private val _state = MutableStateFlow<Resource<Unit>>(Resource.Loading(Unit))
    val state = _state.asStateFlow()

    fun settingNickName(name: String) {
        updateUserUseCase(
            User(
                userId = googleAuthClient.getSignedInUser()?.userId,
                userName = name
            )
        )
            .onStart { Resource.Loading(Unit) }
            .onEach { result ->
                _state.emit(result)
            }
            .catch { e ->
                _state.emit(Resource.Error(e.message ?: ""))
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun settingGender(gender: Int) {
        updateUserUseCase(
            User(
                userId = googleAuthClient.getSignedInUser()?.userId,
                gender = gender
            )
        )
            .onStart { Resource.Loading(Unit) }
            .onEach { result ->
                _state.emit(result)
            }
            .catch { e ->
                _state.emit(Resource.Error(e.message ?: ""))
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

}