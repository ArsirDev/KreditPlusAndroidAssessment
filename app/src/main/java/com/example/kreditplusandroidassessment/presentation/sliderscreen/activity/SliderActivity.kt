package com.example.kreditplusandroidassessment.presentation.sliderscreen.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.example.kreditplusandroidassessment.R
import com.example.kreditplusandroidassessment.base.BaseNavigation
import com.example.kreditplusandroidassessment.databinding.ActivitySliderBinding
import com.example.kreditplusandroidassessment.domain.adapter.slider.SliderAdapter
import com.example.kreditplusandroidassessment.presentation.home.HomeActivity
import com.example.kreditplusandroidassessment.presentation.sliderscreen.viewmodel.SliderViewModel
import com.example.kreditplusandroidassessment.util.removeView
import com.example.kreditplusandroidassessment.util.setOnClickListenerWithDebounce
import com.example.kreditplusandroidassessment.util.showView
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SliderActivity : AppCompatActivity(), BaseNavigation {

    private var _binding: ActivitySliderBinding? = null

    private val binding get() = _binding as ActivitySliderBinding

    private val viewModel: SliderViewModel by inject()

    private lateinit var sliderAdapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInstance()
        setContentView(binding.root)
        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {
            vpSlider.apply {
                adapter = sliderAdapter
                addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {}

                    override fun onPageSelected(position: Int) {
                        TransitionManager.beginDelayedTransition(container)
                        when(position) {
                            2 -> {
                                lifecycleScope.launch {
                                    viewModel.setFirstInstall(true)
                                }
                                btnNext.showView()
                            }
                            else -> {
                                btnNext.removeView()
                            }
                        }
                    }
                    override fun onPageScrollStateChanged(state: Int) {}
                })
            }
            indicator.attachTo(
                viewPager = vpSlider
            )

            btnNext.setOnClickListenerWithDebounce {
                directionNavigation()
            }
        }
    }

    private fun initInstance() {
        _binding = ActivitySliderBinding.inflate(layoutInflater)
        sliderAdapter = SliderAdapter.instance()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun directionNavigation() {
        startActivity(Intent(this, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
        finish()
    }
}