package com.app.test.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
/*
* Custom loading view
* */
object LoadingViewUtils {

    private var dialog: Dialog? = null
    /**
     * show loading view with progress bar
     * @param context
     */
    fun showLoading(context: Context) {
        try {
            if (dialog == null) {
                dialog = Dialog(context)
                dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            }
            dialog?.setCancelable(false)
            val sp = ProgressBar(context)
            dialog?.setContentView(sp)
            dialog?.window!!.setGravity(Gravity.CENTER)

            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog?.getWindow()!!.attributes)

            dialog?.window?.attributes = lp

            val draw = ColorDrawable(Color.TRANSPARENT)
            dialog?.window?.setBackgroundDrawable(draw)
            dialog?.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideLoading() {
        try {
            if (dialog != null) {
                dialog!!.dismiss()
                dialog = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}