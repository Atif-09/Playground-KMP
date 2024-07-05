package expect

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import utils.ContextUtils
import java.util.Locale


actual fun getTextToSpeech(): TTS = AndroidTextToSpeech()

class AndroidTextToSpeech : TTS, TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.US
        }
    }

    override fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    init {
        tts = TextToSpeech(ContextUtils.context, this)
    }
}
