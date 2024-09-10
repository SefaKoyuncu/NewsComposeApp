package com.sefa.newsapp.presentation.auth

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.sefa.newsapp.data.datasources.local.datastore.UserPreferences
import com.sefa.newsapp.utils.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val userPreferences: UserPreferences,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    fun login(email: String, password: String, rememberMe: Boolean)
    {
        if(context.isNetworkAvailable())
        {
            viewModelScope.launch {
                try {
                    _state.value = _state.value.copy(isLoading = true)
                    auth.signInWithEmailAndPassword(email, password).await()

                    _state.value = _state.value.copy(
                        isLoggedIn = true,
                        isLoading = false,
                        error = null
                    )

                    userPreferences.clearAllPreferences()
                    val storedEmail = userPreferences.getUserEmail()
                    if (storedEmail != email) {
                        userPreferences.setUserEmail(email)
                    }

                    if (rememberMe) {
                        userPreferences.setRememberMe(true)
                    }
                } catch (e: Exception) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = e.message ?: "Login failed"
                    )
                }
            }
        }
        else
        {
            _state.value = _state.value.copy(error = "No internet connection")
        }
    }

    fun signup(email: String, password: String)
    {
        if(context.isNetworkAvailable())
        {
            viewModelScope.launch {
                try {
                    _state.value = _state.value.copy(isLoading = true)
                    auth.createUserWithEmailAndPassword(email, password).await()

                    _state.value = _state.value.copy(
                        isLoggedIn = true,
                        isLoading = false,
                        error = null
                    )
                    userPreferences.clearAllPreferences()
                    userPreferences.setUserEmail(email)
                    userPreferences.setRememberMe(false)
                } catch (e: Exception) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = e.message ?: "Signup failed"
                    )
                }
            }
        }
        else
        {
            _state.value = _state.value.copy(error = "No internet connection")
        }
    }

    fun logout()
    {
        if(context.isNetworkAvailable())
        {
            viewModelScope.launch {
                try {
                    auth.signOut()
                    userPreferences.clearAllPreferences()
                    userPreferences.setRememberMe(false)
                    Log.e("AuthViewModel", "Logout successful")
                    _state.value = AuthState(isLoggedIn = false, isLoading = false, error = null)
                } catch (e: Exception) {
                    Log.e("AuthViewModel", "Logout failed: ${e.message}")
                    _state.value =
                        AuthState(isLoggedIn = true, isLoading = false, error = e.message)
                }
            }
        }
        else
        {
            _state.value = _state.value.copy(error = "No internet connection")
        }
    }

    fun sendPasswordResetEmail(email: String)
    {
        if(context.isNetworkAvailable())
        {
            viewModelScope.launch {
                try {
                    _state.value = _state.value.copy(isLoading = true)
                    auth.sendPasswordResetEmail(email).await()
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = "Password reset email sent"
                    )
                } catch (e: Exception) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to send password reset email"
                    )
                }
            }
        }
        else
        {
            _state.value = _state.value.copy(error = "No internet connection")
        }
    }


    fun sendResetEmailToCurrentUser()
    {
        if(context.isNetworkAvailable())
        {
            viewModelScope.launch {
                try {
                    _state.value = _state.value.copy(isLoading = true)

                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        val email = currentUser.email
                        if (!email.isNullOrEmpty()) {
                            auth.sendPasswordResetEmail(email).await()
                            _state.value = _state.value.copy(
                                isLoading = false,
                                error = "Password reset email sent to $email"
                            )
                        } else {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                error = "User email not found"
                            )
                        }
                    }
                } catch (e: Exception) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to send password reset email"
                    )
                }
            }
        }
        else
        {
            _state.value = _state.value.copy(error = "No internet connection")
        }
    }

    fun checkAuthState() {
        if(context.isNetworkAvailable())
        {
            viewModelScope.launch {
                val isRememberMe = userPreferences.isRememberMe()
                val currentUser = auth.currentUser
                if (isRememberMe && currentUser != null) {
                    _state.value = _state.value.copy(isLoggedIn = true)
                } else {
                    _state.value = _state.value.copy(isLoggedIn = false)
                }
            }
        }
        else
        {
            _state.value = _state.value.copy(error = "No internet connection")
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
}