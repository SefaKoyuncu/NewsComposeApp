package com.sefa.newsapp.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sefa.newsapp.data.datasources.local.datastore.UserPreferences
import com.sefa.newsapp.domain.model.NewsUIModel
import com.sefa.newsapp.domain.usecase.DeleteNewsUseCase
import com.sefa.newsapp.domain.usecase.GetIsNewsExistInDbUseCase
import com.sefa.newsapp.domain.usecase.InsertNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel
@Inject constructor(
    private val getIsNewsExistInDbUseCase: GetIsNewsExistInDbUseCase,
    private val insertNewsUseCase: InsertNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase,
    private val userPreferences: UserPreferences
) : ViewModel()
{
    private val _state = mutableStateOf(FavState())
    val state: State<FavState> = _state

    fun checkIfNewsExists(newsId: Long)
    {
        viewModelScope.launch {
            val userEmail = userPreferences.getUserEmail()
            if (!userEmail.isNullOrEmpty())
            {
                getIsNewsExistInDbUseCase.invoke(newsId,userEmail)
                    .distinctUntilChanged()
                    .collect { exist ->
                        _state.value = FavState(isFav = exist)
                    }
            }
        }
    }

    fun toggleFavorite(newsUIModel: NewsUIModel)
    {
        viewModelScope.launch {
            val userEmail = userPreferences.getUserEmail()
            if (!userEmail.isNullOrEmpty()) {

              val news =  newsUIModel.copy(userEmail = userEmail)

                val isFav = state.value.isFav

                if (isFav)
                    deleteNewsUseCase.invoke(news.id ?: 1,userEmail)
                else
                    insertNewsUseCase.invoke(news)

                _state.value = _state.value.copy(isFav = !isFav)
            }
        }
    }
}