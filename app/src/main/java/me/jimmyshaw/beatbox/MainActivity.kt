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

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // (7)
        beatBox = BeatBox(assets)

        // (1) Data binding library auto-generates ActivityMainBinding, which holds on
        // to the view hierarchy in the root property. The binding also holds on
        // to named references tagged with an android:id in the layout file.
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            // (4)
            // (12) Pass actual list of sounds to adapter.
            adapter = SoundAdapter(beatBox.sounds)
        }
    }

    // (2) Data binding library auto-generates ListItemSoundBinding from list_item_sound.xml.
    private inner class SoundHolder(private val binding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // (16A) Create a view model object and attach it to its binding class.
        init {
            binding.viewModel = SoundViewModel()
        }

        // (16B) This bind function updates the data that the view model
        // is working with.
        fun bind(sound: Sound) {
            binding.apply {
                viewModel?.sound = sound
                // Call this isn't normally necessary but here data is updating
                // inside a recycler view, which updates at a high speed. Calling
                // this forces the layout to immediately update itself, rather than
                // wait a millisecond or two. This keeps the recycler view in sync
                // with its adapter.
                executePendingBindings()
            }
        }

    }

    // (3)
    // (11A) Wire up list of sound with this adapter.
    private inner class SoundAdapter(private val sounds: List<Sound>) : RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater, R.layout.list_item_sound, parent, false
            )
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {}

        // (11B) Assign the actual size to the item count.
        override fun getItemCount(): Int = sounds.size

    }
}