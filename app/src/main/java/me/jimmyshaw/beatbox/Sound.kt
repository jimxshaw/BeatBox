package me.jimmyshaw.beatbox

private const val WAV = ".wav"

// (8) Sound class is needed to keep track of each
// sound's information that will be presented to the user.
class Sound(val assetPath: String) {

    val name = assetPath.split("/")
                        .last()
                        .removeSuffix(WAV)
}