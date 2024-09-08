package com.sefa.newsapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class News (

  @SerializedName("uri"            ) var uri           : String?           = null,
  @SerializedName("url"            ) var url           : String?           = null,
  @SerializedName("id"             ) var id            : Long?              = null,
  @SerializedName("asset_id"       ) var assetId       : Long?              = null,
  @SerializedName("source"         ) var source        : String?           = null,
  @SerializedName("published_date" ) var publishedDate : String?           = null,
  @SerializedName("updated"        ) var updated       : String?           = null,
  @SerializedName("section"        ) var section       : String?           = null,
  @SerializedName("subsection"     ) var subsection    : String?           = null,
  @SerializedName("nytdsection"    ) var nytdsection   : String?           = null,
  @SerializedName("adx_keywords"   ) var adxKeywords   : String?           = null,
  @SerializedName("column"         ) var column        : String?           = null,
  @SerializedName("byline"         ) var byline        : String?           = null,
  @SerializedName("type"           ) var type          : String?           = null,
  @SerializedName("title"          ) var title         : String?           = null,
  @SerializedName("abstract"       ) var abstract      : String?           = null,
  @SerializedName("des_facet"      ) var desFacet      : List<String> = listOf(),
  @SerializedName("org_facet"      ) var orgFacet      : List<String> = listOf(),
  @SerializedName("per_facet"      ) var perFacet      : List<String> = listOf(),
  @SerializedName("geo_facet"      ) var geoFacet      : List<String> = listOf(),
  @SerializedName("media"          ) var media         : List<Media>  = listOf(),
  @SerializedName("eta_id"         ) var etaId         : Int?              = null

) : Parcelable