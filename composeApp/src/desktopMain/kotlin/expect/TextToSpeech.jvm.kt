package expect

// desktopMain/src/desktopMain/kotlin/com/example/tts/TextToSpeechDesktop.kt

import com.sun.speech.freetts.Age
import com.sun.speech.freetts.Gender
import com.sun.speech.freetts.Voice
import com.sun.speech.freetts.audio.JavaClipAudioPlayer
import com.sun.speech.freetts.en.us.CMUDiphoneVoice
import com.sun.speech.freetts.en.us.CMULexicon
import java.util.Locale

actual fun getTextToSpeech(): TTS = DesktopTextToSpeech()

class DesktopTextToSpeech : TTS {
    private val voice: Voice

    init {
        try {
            // Initialize the lexicon
            val lexicon = CMULexicon("cmulex")

            // Load the voice data file (cmu_us_kal16.bin)
            val voiceData = this::class.java.getResource("cmu_us_kal.bin")
                ?: throw IllegalStateException("Voice data file not found")

            // Initialize the voice
            voice = CMUDiphoneVoice(
                "kevin",
                Gender.MALE,
                Age.YOUNGER_ADULT,
                "default 16-bit diphone voice",
                Locale.US,
                "general",
                "cmu",
                lexicon,
                voiceData
            )

            // Set the AudioPlayer
            voice.audioPlayer = JavaClipAudioPlayer()

            // Load the Voice
            voice.allocate()
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalStateException("Error initializing FreeTTS: ${e.message}")
        }
    }

    override fun speak(text: String) {
        try {
            voice.speak(text)
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalStateException("Error during speech synthesis: ${e.message}")
        }
    }
}



