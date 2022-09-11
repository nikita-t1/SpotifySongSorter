package viewmodel

import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseViewModel : KoinComponent {

    val viewModelScope by inject<CoroutineScope>()

}