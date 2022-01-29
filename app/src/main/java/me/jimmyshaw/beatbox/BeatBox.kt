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

    // (6)
    fun loadSounds(): List<String> {
        return try {
            val soundNames = assets.list(SOUNDS_FOLDER)!!
            Log.d(TAG, "Found ${soundNames.size} sounds")
            soundNames.asList()
        } catch (e: Exception) {
            Log.e(TAG, "Could not list assets", e)
            emptyList()
        }
    }
}