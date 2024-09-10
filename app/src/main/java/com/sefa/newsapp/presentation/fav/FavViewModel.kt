package com.sefa.newsapp.presentation.fav

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.sefa.newsapp.data.datasources.local.datastore.UserPreferences
import com.sefa.newsapp.domain.usecase.GetFavListUseCase
import com.sefa.newsapp.utils.Resource
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@HiltViewModel
class FavViewModel
@Inject constructor(
    private val getFavListUseCase: GetFavListUseCase,
    private val userPreferences: UserPreferences,
    ) : ViewModel()
{
    private val _state = mutableStateOf(FavListState())
    val state: State<FavListState> = _state

    init {
        getFavList()
    }
    fun getFavList()
    {
        viewModelScope.launch {

            val userEmail = userPreferences.getUserEmail()
            if (!userEmail.isNullOrEmpty())
            {
                getFavListUseCase.invoke(userEmail)
                    .distinctUntilChanged()
                    .collect { resource ->
                        when (resource) {
                            is Resource.Success -> {
                                _state.value = FavListState(favList = resource.data ?: emptyList())
                            }

                            is Resource.Loading -> {
                                _state.value = FavListState(isLoading = true)
                            }

                            is Resource.Error -> {
                                _state.value =
                                    FavListState(error = resource.message ?: "An error occurred")
                            }
                        }
                    }
            }
            else
            {
                _state.value =
                    FavListState(error =  "No data found")
            }
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(error = "")
    }
}