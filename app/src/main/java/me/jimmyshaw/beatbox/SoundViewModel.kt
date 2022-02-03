package me.jimmyshaw.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

// (14) Following the MVVM architecture, this view model
// will be responsible for preparing the data for
// the view to display. Unlike MVC, there's no controller
// as activities and fragments are considered part of the view.

// (18A) Make the view model communicate with the layout file
// by implementing the Observable interface.
class SoundViewModel : BaseObservable() {

    var sound: Sound? = null
        set(sound) {
            field = sound
            // (18B) When this is called here, it notifies the binding
            // class that all the Bindable properties on the objects
            // have been updated. The binding class then runs the code
            // inside the binding mustaches again to repopulate the view.
            notifyChange()
        }

    // (18C)
    @get:Bindable
    val title: String?
        get() = sound?.name
}