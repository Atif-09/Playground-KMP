package expect

// iosMain/src/iosMain/kotlin/com/example/tts/TextToSpeechIOS.kt

import platform.AVFoundation.AVSpeechSynthesizer
import platform.AVFoundation.AVSpeechUtterance

actual fun getTextToSpeech(): TTS = IOSTextToSpeech()

class IOSTextToSpeech : TTS {
    private val synthesizer = AVSpeechSynthesizer()

    override fun speak(text: String) {
        val utterance = AVSpeechUtterance.speechUtteranceWithString(text)
        synthesizer.speakUtterance(utterance)
    }
}
