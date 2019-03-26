package kotlinx.coroutines.flow.operators

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.builders.*
import kotlinx.coroutines.flow.terminal.*

/**
 * Returns the flow that emits the count of values in the original stream.
 */
public fun <T : Any> Flow<T>.count(): Flow<Long> = flow {
    var i = 0L
    collect {
        ++i
    }
    emit(i)
}
