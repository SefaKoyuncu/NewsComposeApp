package com.sefa.newsapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Media (

  @SerializedName("type"                     ) var type                   : String?                   = null,
  @SerializedName("subtype"                  ) var subtype                : String?                   = null,
  @SerializedName("caption"                  ) var caption                : String?                   = null,
  @SerializedName("copyright"                ) var copyright              : String?                   = null,
  @SerializedName("approved_for_syndication" ) var approvedForSyndication : Int?                      = null,
  @SerializedName("media-metadata"           ) var mediaMetadata         : List<MediaMetadata> = listOf()

) : Parcelable