package com.sefa.newsapp.presentation.fav

import com.sefa.newsapp.domain.model.NewsUIModel

data class FavListState(
    val isLoading: Boolean = false,
    val favList: List<NewsUIModel> = emptyList(),
    val error: String = ""
)
