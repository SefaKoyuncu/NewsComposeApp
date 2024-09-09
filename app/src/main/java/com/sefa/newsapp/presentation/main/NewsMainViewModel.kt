package com.sefa.newsapp.presentation.main

import android.content.Context
import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sefa.newsapp.domain.usecase.GetNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.sefa.newsapp.utils.Resource
import com.sefa.newsapp.utils.isNetworkAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@HiltViewModel
class NewsMainViewModel
@Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase,
    @ApplicationContext private val context: Context
) : ViewModel()
{
    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getNewsList()
    }
    fun getNewsList()
    {
        if(context.isNetworkAvailable())
        {
            viewModelScope.launch {
                getNewsListUseCase.invoke()
                    .distinctUntilChanged()
                    .collect { resource ->

                        Log.e("TAG", resource.data?.size.toString())

                        when (resource) {
                            is Resource.Success -> {
                                _state.value =
                                    NewsListState(newsList = resource.data ?: emptyList())
                            }

                            is Resource.Loading -> {
                                _state.value = NewsListState(isLoading = true)
                            }

                            is Resource.Error -> {
                                _state.value =
                                    NewsListState(error = resource.message ?: "An error occurred")
                            }
                        }
                    }
            }
        }
        else
        {
            _state.value = _state.value.copy(error = "No internet connection")
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(error = "")
    }
}