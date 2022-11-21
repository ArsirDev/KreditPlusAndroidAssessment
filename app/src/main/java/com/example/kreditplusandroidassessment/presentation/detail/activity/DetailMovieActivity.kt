package com.example.kreditplusandroidassessment.presentation.detail.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kreditplusandroidassessment.R
import com.example.kreditplusandroidassessment.data.remote.dto.DetailResponse
import com.example.kreditplusandroidassessment.data.remote.dto.VideoResultsItem
import com.example.kreditplusandroidassessment.databinding.ActivityDetailMovieBinding
import com.example.kreditplusandroidassessment.presentation.detail.viewmodel.DetailMovieViewModel
import com.example.kreditplusandroidassessment.util.*
import com.example.kreditplusandroidassessment.util.Extended.ID
import com.google.android.material.chip.Chip
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class DetailMovieActivity : AppCompatActivity() {

    private var _binding: ActivityDetailMovieBinding? = null

    private val binding get() = _binding as ActivityDetailMovieBinding

    private val detailMovieViewModel: DetailMovieViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInstance()
        setContentView(binding.root)
        initBundle()
        initToolBar()
        initLaunch()
    }

    private fun initLaunch() {
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    detailMovieViewModel.state.collectLatest { state ->
                        with(binding.lyContent) {
                            if (state.isLoading) {
                                pbLoading?.showView()
                            } else {
                                pbLoading?.removeView()
                            }
                        }
                        initView(state.detail)
                        initYoutube(state.video, state.detail)
                    }
                }
            }
        }
    }

    private fun initView(detailResponse: DetailResponse?) {
        with(binding.lyContent) {
            tvTitle?.text = detailResponse?.title
            tvRelease?.text = detailResponse?.releaseDate?.dateConverter()
            tvVote?.text = detailResponse?.voteAverage.toString()
            tvDescription?.text = detailResponse?.overview
            detailResponse?.genres?.forEach {
                addChip(it.name)
            }
        }
    }

    private fun addChip(genre: String) {
        val chip = layoutInflater.inflate(R.layout.item_chip, binding.lyContent.cGroup, false) as Chip
        chip.text = genre
        binding.lyContent.cGroup?.addView(chip)
    }


    private fun initYoutube(
        youtubeResponse: List<VideoResultsItem>?,
        detailResponse: DetailResponse?
    ) {
        with(binding) {
            ivImage.apply {
                loadImage(detailResponse?.backdropPath.toString())
                setOnClickListenerWithDebounce {
                    ytVideo.showView()
                    toolbarLayout.title = " "
                }
            }
            binding.toolbarLayout.title = detailResponse?.title
        }

        binding.ytVideo.apply {
            lifecycle.addObserver(this)
            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youtubeResponse?.map { data ->
                        youTubePlayer.cueVideo(data.key, 0f)
                        onCollaption(youTubePlayer, detailResponse)
                    }
                }
            })
        }
    }

    private fun onCollaption(youTubePlayer: YouTubePlayer, detailResponse: DetailResponse?) {
        var isShow = true
        var scrollRange = -1
        binding.appBar.addOnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.toolbarLayout.title = detailResponse?.title
                isShow = true
            } else if (isShow) {
                youTubePlayer.pause()
                binding.ytVideo.removeView()
                binding.toolbarLayout.title = " "
                isShow = false
            }
        }
    }

    private fun initBundle() {
        intent.extras?.getInt(ID)?.let { id ->
            detailMovieViewModel.getDetail(id)
            detailMovieViewModel.getVideoMovie(id)
        }
    }

    private fun initToolBar() {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    private fun initInstance() {
        _binding = ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}