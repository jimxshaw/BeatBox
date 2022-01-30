package me.jimmyshaw.beatbox

import android.content.res.AssetManager
import android.util.Log

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"

// (5) Assets are accessed from AssetManager.
// AssetManager can be gotten from any context.
// Knowing which context isn't vital as all any context's
// AssetManager will be wired to the same set of assets.
class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>

    init {
        sounds = loadSounds()
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

            sounds.add(sound)
        }

        return sounds
    }
}