package me.jimmyshaw.beatbox

// (14) Following the MVVM architecture, this view model
// will be responsible for preparing the data for
// the view to display. Unlike MVC, there's no controller
// as activities and fragments are considered part of the view.
class SoundViewModel {

    var sound: Sound? = null
        set(sound) {
            field = sound
        }

    val title: String?
        get() = sound?.name
}