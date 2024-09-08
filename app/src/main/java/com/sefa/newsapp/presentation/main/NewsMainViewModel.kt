package com.sefa.newsapp.presentation.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sefa.newsapp.domain.usecase.GetNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.sefa.newsapp.utils.Resource
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@HiltViewModel
class NewsMainViewModel
@Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel()
{
    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getNewsList()
    }
    fun getNewsList()
    {
        viewModelScope.launch {
            getNewsListUseCase.invoke()
                .distinctUntilChanged()
                .collect { resource->

                    Log.e("TAG", resource.data?.size.toString())

                    when (resource) {
                        is Resource.Success -> {
                            _state.value = NewsListState(movieList = resource.data ?: emptyList())
                        }

                        is Resource.Loading -> {
                            _state.value = NewsListState(isLoading = true)
                        }

                        is Resource.Error -> {
                            _state.value = NewsListState(error = resource.message ?: "An error occurred")
                        }
                    }
                }
        }
    }
}