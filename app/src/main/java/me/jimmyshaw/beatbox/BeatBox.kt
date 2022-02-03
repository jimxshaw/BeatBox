package me.jimmyshaw.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"

// (19A) How many sounds can play at any given time.
private const val MAX_SOUNDS = 5

// (5) Assets are accessed from AssetManager.
// AssetManager can be gotten from any context.
// Knowing which context isn't vital as all any context's
// AssetManager will be wired to the same set of assets.
class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>

    // (19B) SoundPool can load many sounds in to memory and can control
    // max number of sounds that can play at any time.
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()

    init {
        sounds = loadSounds()
    }

    // (21) The SoundPool plays sounds right away but must load
    // sounds first, hence the id labeling each loaded sound.
    private fun load(sound: Sound) {
        val assetFileDescriptor: AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(assetFileDescriptor, 1)
        sound.soundId = soundId
    }

    // (6)
    // (13) Make method private as it's no longer being called
    // outside of the init block.
    private fun loadSounds(): List<Sound> {
        val soundNames: Array<String>

        try {
            // (9) The assignment will only succeed if the
            // assets list is NOT null. NullPointerException
            // will be thrown otherwise.
            soundNames = assets.list(SOUNDS_FOLDER)!!
        } catch (e: Exception) {
            Log.e(TAG, "Cound not list assets", e)

            return emptyList()
        }

        val sounds = mutableListOf<Sound>()

        // (10)
        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)

            // (22) Load sound into the SoundPool.
            try {
                load(sound)
                sounds.add(sound)
            } catch (e: IOException) {
                Log.e(TAG, "Could not load sound $filename", e)
            }
        }

        return sounds
    }
}