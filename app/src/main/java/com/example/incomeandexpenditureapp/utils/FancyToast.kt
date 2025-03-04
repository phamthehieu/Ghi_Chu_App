package com.example.incomeandexpenditureapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import com.example.incomeandexpenditureapp.R

class FancyToast(context: Context) : Toast(context) {

    @Target(AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER)
    @IntDef(SUCCESS, WARNING, ERROR, INFO, DEFAULT, CONFUSING)
    @Retention(AnnotationRetention.SOURCE)
    annotation class LayoutType

    companion object {
        const val SUCCESS = 1
        const val WARNING = 2
        const val ERROR = 3
        const val INFO = 4
        const val DEFAULT = 5
        const val CONFUSING = 6

        const val LENGTH_SHORT = Toast.LENGTH_SHORT;
        const val LENGTH_LONG = Toast.LENGTH_LONG;

        @Target(AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER)
        @IntDef(LENGTH_SHORT, LENGTH_LONG)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Duration

        @SuppressLint("MissingInflatedId")
        fun makeText(
            context: Context,
            message: CharSequence,
            @Duration duration: Int,
            @LayoutType type: Int,
            androidIcon: Boolean
        ): Toast {
            val toast = Toast(context)
            toast.duration = duration
            val layout = LayoutInflater.from(context).inflate(R.layout.fancytoast_layout, null, false)
            val l1: TextView = layout.findViewById(R.id.toast_text)
            val linearLayout: LinearLayout = layout.findViewById(R.id.toast_type)
            val img: ImageView = layout.findViewById(R.id.toast_icon)
            val img1: ImageView = layout.findViewById(R.id.imageView4)

            l1.text = message
            img1.visibility = if (androidIcon) View.VISIBLE else View.GONE

            when (type) {
                SUCCESS -> {
                    linearLayout.setBackgroundResource(R.drawable.success_shape)
                    img.setImageResource(R.drawable.ic_check_black_24dp)
                }
                WARNING -> {
                    linearLayout.setBackgroundResource(R.drawable.warning_shape)
                    img.setImageResource(R.drawable.ic_pan_tool_black_24dp)
                }
                ERROR -> {
                    linearLayout.setBackgroundResource(R.drawable.error_shape)
                    img.setImageResource(R.drawable.ic_warning_24)
                }
                INFO -> {
                    linearLayout.setBackgroundResource(R.drawable.info_shape)
                    img.setImageResource(R.drawable.ic_info_outline_black_24dp)
                }
                DEFAULT -> {
                    linearLayout.setBackgroundResource(R.drawable.default_shape)
                    img.visibility = View.GONE
                }
                CONFUSING -> {
                    linearLayout.setBackgroundResource(R.drawable.confusing_shape)
                    img.setImageResource(R.drawable.ic_refresh_black_24dp)
                }
            }

            toast.view = layout
            return toast
        }

        @SuppressLint("MissingInflatedId")
        fun makeText(
            context: Context,
            message: CharSequence,
            @Duration duration: Int,
            @LayoutType type: Int,
            @DrawableRes imageResource: Int,
            androidIcon: Boolean
        ): Toast {
            val toast = Toast(context)
            toast.duration = duration
            val layout = LayoutInflater.from(context).inflate(R.layout.fancytoast_layout, null, false)
            val l1: TextView = layout.findViewById(R.id.toast_text)
            val linearLayout: LinearLayout = layout.findViewById(R.id.toast_type)
            val img: ImageView = layout.findViewById(R.id.toast_icon)
            val img1: ImageView = layout.findViewById(R.id.imageView4)

            l1.text = message
            img.setImageResource(imageResource)
            img1.visibility = if (androidIcon) View.VISIBLE else View.GONE

            when (type) {
                SUCCESS -> linearLayout.setBackgroundResource(R.drawable.success_shape)
                WARNING -> linearLayout.setBackgroundResource(R.drawable.warning_shape)
                ERROR -> linearLayout.setBackgroundResource(R.drawable.error_shape)
                INFO -> linearLayout.setBackgroundResource(R.drawable.info_shape)
                DEFAULT -> {
                    linearLayout.setBackgroundResource(R.drawable.default_shape)
                    img.visibility = View.GONE
                }
                CONFUSING -> linearLayout.setBackgroundResource(R.drawable.confusing_shape)
                else -> {
                    linearLayout.setBackgroundResource(R.drawable.default_shape)
                    img.visibility = View.GONE
                }
            }

            toast.view = layout
            return toast
        }
    }
}