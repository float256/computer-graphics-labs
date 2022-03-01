package image

import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*

class ImagePath {
    private var changePathObservableSubject: PublishSubject<Optional<String>> = PublishSubject.create()

    var path: String? = null
        set(value) {
            changePathObservableSubject.onNext(Optional.ofNullable(value))
            field = value
        }

    fun doOnPathChanged(observer: (String?) -> Unit) {
        changePathObservableSubject.subscribe { optionalString ->
            when (optionalString) {
                Optional.empty<String>() -> observer(null)
                else -> observer(optionalString.get())
            }
        }
    }
}