package com.sefa.newsapp.data.mapper

import com.sefa.newsapp.data.model.News
import com.sefa.newsapp.domain.model.NewsUIModel
import com.sefa.newsapp.utils.Constants.NO_IMAGE

fun News.asNewsUIModel() = NewsUIModel(
    id = id,
    url = url,
    publishedDate = publishedDate,
    section = section,
    subsection = subsection,
    byline = byline,
    title = title,
    abstract = abstract,
    imageUrl = media.firstOrNull()?.mediaMetadata?.getOrNull(2)?.url ?: NO_IMAGE // Güvenli erişim
)

