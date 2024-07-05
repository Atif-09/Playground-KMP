package utils

import android.content.Context

object ContextUtils {

    private var appContext: Context? = null

    val context
        get() = appContext
            ?: error("Android context has not been set. Please call setContext in your Application's onCreate.")

    fun setContext(context: Context) {
        appContext = context.applicationContext
    }
}
