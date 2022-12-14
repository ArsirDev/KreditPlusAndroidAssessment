package com.example.kreditplusandroidassessment.presentation.splashscreen.activity

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kreditplusandroidassessment.base.BaseNavigation
import com.example.kreditplusandroidassessment.databinding.ActivitySplashBinding
import com.example.kreditplusandroidassessment.presentation.home.HomeActivity
import com.example.kreditplusandroidassessment.presentation.splashscreen.viewmodel.SplashViewModel
import com.example.kreditplusandroidassessment.presentation.welcomescreen.WelcomeActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), BaseNavigation {

    private var _binding: ActivitySplashBinding? = null

    private val binding get() = _binding as ActivitySplashBinding

    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        initInstallSplashScreen()
        super.onCreate(savedInstanceState)
        initInstance()
        setContentView(binding.root)
        initStartLoading()
    }

    private fun initStartLoading() {
        findViewById<View>(android.R.id.content)?.apply {
            viewTreeObserver.addOnPreDrawListener (
                object: ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        return if (viewModel.isLoading.value) {
                            viewTreeObserver.removeOnPreDrawListener(this)
                            true
                        } else false
                    }
                }
            )
        }
    }

    private fun initInstallSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
            directionNavigation()
            setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(
                    splashScreenView.view,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.view.height.toFloat()
                ).apply {
                    interpolator = AnticipateInterpolator()
                    duration = 1000L
                    doOnEnd {
                        splashScreenView.remove()
                    }
                }.start()
            }
        }
    }

    private fun initInstance() {
        _binding = ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onBackPressed() {
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun directionNavigation() {
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collectLatest { status ->
                        if (status) {
                            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                            finish()
                        } else {
                            startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }
}