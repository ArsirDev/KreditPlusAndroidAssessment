package com.example.kreditplusandroidassessment.util

import android.graphics.Paint
import android.os.SystemClock
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.kreditplusandroidassessment.BuildConfig.ORIGINAL_IMAGE_URL
import com.example.kreditplusandroidassessment.R
import com.example.kreditplusandroidassessment.util.MESSAGE.STATUS_ERROR
import com.example.kreditplusandroidassessment.util.MESSAGE.STATUS_SUCCESS
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

fun View.startAnimation(animation: Animation, onFinish: () -> Unit) {
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) {

        }

        override fun onAnimationEnd(p0: Animation?) {
            onFinish()
        }

        override fun onAnimationRepeat(p0: Animation?) {

        }

    })
    this.startAnimation(animation)
}

fun View.setOnClickListenerWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(p0: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun String.dateConverter(): String {
    val defaultDate: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    val convertDate: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
    val ld: LocalDate = LocalDate.parse(this, defaultDate)
    return convertDate.format(ld)
}

fun ImageView.loadImage(imageUrl: String, roundedValue: Int = 24) {
    Glide.with(this.context)
        .load(imageUrl.createImageUrl())
        .error(ContextCompat.getDrawable(this.context, R.drawable.ic_broken_image_black))
        .placeholder(ContextCompat.getDrawable(this.context, R.drawable.ic_image_black))
        .transform(RoundedCorners(roundedValue))
        .into(this)
}

fun String.createImageUrl(): String {
    return ORIGINAL_IMAGE_URL + this
}

fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.removeView() {
    this.visibility = View.GONE
}

fun ArrayList<View>.removeAllView() {
    this.forEach {
        it.removeView()
    }
}

fun snackbar(view: View, message: String, type: String, duration: Int = Snackbar.LENGTH_SHORT) {
    when(type) {
        STATUS_SUCCESS -> {
            Snackbar.make(view, message, duration).apply {
                this.view.setBackgroundColor(ContextCompat.getColor(this.context, R.color.green_color))
            }.show()
        }
        STATUS_ERROR -> {
            Snackbar.make(view, message, duration).apply {
                this.view.setBackgroundColor(ContextCompat.getColor(this.context, R.color.red_color))
            }.show()
        }
    }
}