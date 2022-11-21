package com.example.kreditplusandroidassessment.data.remote.dto

import com.example.kreditplusandroidassessment.domain.model.NowPlaying
import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(

	@field:SerializedName("dates")
	val dates: Dates,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<NowPlaying>,

	@field:SerializedName("total_results")
	val totalResults: Int
) {
	fun nowPlayingMovieList(): List<NowPlaying> {
		return results.map { nowPlaying ->
			NowPlaying(
				 overview = nowPlaying.overview,
				 originalLanguage = nowPlaying.originalLanguage,
				 originalTitle = nowPlaying.originalTitle,
				 video = nowPlaying.video,
				 title = nowPlaying.title,
				 genreIds = nowPlaying.genreIds,
				 posterPath = nowPlaying.posterPath,
				 backdropPath = nowPlaying.backdropPath,
				 releaseDate = nowPlaying.releaseDate,
				 popularity = nowPlaying.popularity,
				 voteAverage = nowPlaying.voteAverage,
				 id = nowPlaying.id,
				 adult = nowPlaying.adult,
				 voteCount = nowPlaying.voteCount
			)
		}
	}
}

data class Dates(

	@field:SerializedName("maximum")
	val maximum: String,

	@field:SerializedName("minimum")
	val minimum: String
)