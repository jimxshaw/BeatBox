package me.jimmyshaw.beatbox

private const val WAV = ".wav"

// (8) Sound class is needed to keep track of each
// sound's information that will be presented to the user.

// (20) Sound class must have an id to work with the SoundPool
// object. The SoundPool plays sounds right away but must load
// sounds first, hence the id labeling each loaded sound.
class Sound(val assetPath: String, var soundId: Int? = null) {

    val name = assetPath.split("/")
                        .last()
                        .removeSuffix(WAV)
}