package expect

interface TTS {
    fun speak(text: String)
}

expect fun getTextToSpeech(): TTS