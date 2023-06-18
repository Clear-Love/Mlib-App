package com.lmio.mlib.utils

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams
import com.lmio.mlib.R

class SnackbarUtil {
    companion object {
        fun showSuccessMessage(view: View, message: String) {
            showSnackbar(view, message, R.color.success)
        }

        fun showWarningMessage(view: View, message: String) {
            showSnackbar(view, message, R.color.warning)
        }

        fun showErrorMessage(view: View, message: String) {
            showSnackbar(view, message, R.color.error)
        }

        fun showMessage(view: View, message: String,) {
            showSnackbar(view, message, R.color.white)
        }

        @SuppressLint("ResourceAsColor")
        private fun showSnackbar(view: View, message: String, backgroundColor: Int) {
            val bar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            bar.view.setBackgroundColor(ContextCompat.getColor(view.context, backgroundColor))
            bar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
            bar.setActionTextColor(R.color.indigo)
            bar.setTextColor(R.color.black)
            bar.setAction("关闭") {
                // 关闭Snackbar
                bar.dismiss()
            }
            val layoutParams = bar.view.layoutParams as LayoutParams
            layoutParams.gravity = Gravity.TOP
            bar.view.layoutParams = layoutParams
            bar.show()
        }
    }
}