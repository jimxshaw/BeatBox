package me.jimmyshaw.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.jimmyshaw.beatbox.databinding.ActivityMainBinding
import me.jimmyshaw.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // (1) Data binding library auto-generates ActivityMainBinding, which holds on
        // to the view hierarchy in the root property. The binding also holds on
        // to named references tagged with an android:id in the layout file.
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
        }
    }

    // (2) Data binding library auto-generates ListItemSoundBinding from list_item_sound.xml.
    private inner class SoundHolder(private val binding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}