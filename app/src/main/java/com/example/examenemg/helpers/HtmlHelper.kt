package com.example.examenemg.helpers

import android.os.Build
import android.text.Html
import android.text.Spanned

object HtmlHelper {
    fun castSource(source: String): Spanned{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(source)
        }
    }
}