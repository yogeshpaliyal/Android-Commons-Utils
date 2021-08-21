@file:JvmName(name = "DisplayHelper")

package com.yogeshpaliyal.commons_utils

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager


    fun getScreenSize(context: Context): IntArray {
        val displaymetrics = DisplayMetrics()
        val windowManager: WindowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displaymetrics)
            val h: Int = displaymetrics.heightPixels
            val w: Int = displaymetrics.widthPixels
            return intArrayOf(w, h)
        }
        return intArrayOf(0, 0)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarGradient(activity: Activity, drawableResId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = activity.getWindow()
            val background: Drawable = activity.getResources().getDrawable(drawableResId)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent))
            window.setBackgroundDrawable(background)
        }
    }

    fun getResourceDimenInPx(context: Context, resId: Int): Float {
        return (context.getResources().getDimension(resId) / context.getResources()
            .getDisplayMetrics().scaledDensity)
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A int value to represent px equivalent to dp depending on device density
     */
    fun convertDpToPx(context: Context, dp: Float): Int {
        val resources: Resources = context.getResources()
        val metrics: DisplayMetrics = resources.getDisplayMetrics()
        val px: Float = dp * (metrics.densityDpi as Float / DisplayMetrics.DENSITY_DEFAULT)
        return px.toInt()
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A int value to represent dp equivalent to px value
     */
    fun convertPxToDp(context: Context, px: Float): Int {
        val resources: Resources = context.getResources()
        val metrics: DisplayMetrics = resources.getDisplayMetrics()
        val dp: Float = px / (metrics.densityDpi as Float / DisplayMetrics.DENSITY_DEFAULT)
        return dp.toInt()
    }

    fun convertPxToSp(context: Context, px: Float): Int {
        return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity).toInt()
    }

    fun convertSpToPx(context: Context, sp: Float): Int {
        return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity).toInt()
    }
