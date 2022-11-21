package com.example.kreditplusandroidassessment.data.remote.params

object ParamsRepo {
    fun nowPlayingParams(
        page: Int,
        language: String
    ): HashMap<String, Any> {
        val params: HashMap<String, Any> = HashMap()

        params["page"] = page
        params["language"] = language

        return params
    }
}