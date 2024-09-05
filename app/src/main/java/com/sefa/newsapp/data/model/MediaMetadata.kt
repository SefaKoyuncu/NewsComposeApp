package com.sefa.newsapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class MediaMetadata (

  @SerializedName("url"    ) var url    : String? = null,
  @SerializedName("format" ) var format : String? = null,
  @SerializedName("height" ) var height : Int?    = null,
  @SerializedName("width"  ) var width  : Int?    = null

) : Parcelable