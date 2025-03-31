package com.moviescatalog.domain.model


import androidx.annotation.StringRes
import com.moviescatalog.domain.R

enum class MovieCategory(
    @StringRes val titleRes: Int,
    val sortBy: String
) {
    POPULAR(R.string.title_popular, "popularity.desc"),
    REVENUE(R.string.title_revenue, "revenue.desc"),
    TOP_RATED(R.string.title_top_rated, "vote_average.desc")
}