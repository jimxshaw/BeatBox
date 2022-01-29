package me.jimmyshaw.beatbox

import android.app.LauncherActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
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
            // (4)
            adapter = SoundAdapter()
        }
    }

    // (2) Data binding library auto-generates ListItemSoundBinding from list_item_sound.xml.
    private inner class SoundHolder(private val binding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    // (3)
    private inner class SoundAdapter() : RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater, R.layout.list_item_sound, parent, false
            )
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {}

        override fun getItemCount(): Int = 0

    }
}