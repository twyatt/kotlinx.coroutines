package kotlinx.coroutines.flow.operators

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.builders.*
import kotlinx.coroutines.flow.terminal.*

/**
 * Transforms the given flow into a flow of elements that match given [predicate]
 */
public inline fun <T : Any> Flow<T>.filter(crossinline predicate: suspend (T) -> Boolean): Flow<T> = flow {
    // TODO inliner 1.3.30
    collect { value ->
        if (predicate(value)) emit(value)
    }
}