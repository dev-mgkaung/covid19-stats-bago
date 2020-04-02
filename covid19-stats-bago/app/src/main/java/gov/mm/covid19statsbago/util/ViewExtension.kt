package gov.mm.covid19statsbago.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * @author kyawhtut
 * @date 02/04/2020
 */
object ViewExtension

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Context?.getInflateView(
    @LayoutRes layoutId: Int, container: ViewGroup? = null,
    attachToRoot: Boolean = false
) = LayoutInflater.from(this).inflate(layoutId, container, attachToRoot)
