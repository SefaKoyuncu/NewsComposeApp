package com.sefa.newsapp.presentation.main

import com.sefa.newsapp.domain.model.NewsUIModel

data class NewsListState(
    val isLoading: Boolean = false,
    val movieList: List<NewsUIModel> = emptyList(),
    val error: String = ""
)
