package com.sefa.newsapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "news")
@Serializable
@Parcelize
data class NewsUIModel (

  var url           : String?           = null,
  @PrimaryKey //autoGenerate = true
  var id            : Long?             = null,
  var publishedDate : String?           = null,
  var section       : String?           = null,
  var subsection    : String?           = null,
  var byline        : String?           = null,
  var title         : String?           = null,
  var abstract      : String?           = null,
  var imageUrl      : String?           = null,
  val userEmail     : String?           = null
) : Parcelable