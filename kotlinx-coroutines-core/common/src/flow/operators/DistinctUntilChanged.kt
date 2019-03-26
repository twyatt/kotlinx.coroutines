package kotlinx.coroutines.flow.operators

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.builders.*
import kotlinx.coroutines.flow.terminal.*

/**
 * Returns flow where all subsequent repetitions of the same value are filtered out.
 */
public fun <T : Any> Flow<T>.distinctUntilChanged(): Flow<T> = distinctUntilChanged { it }

/**
 * Returns flow where all subsequent repetitions of the same key are filtered out, where
 * key is extracted with [keySelector] function.
 */
public fun <T : Any, K : Any> Flow<T>.distinctUntilChanged(keySelector: (T) -> K): Flow<T> =
    flow {
        var previousKey: K? = null
        collect { value ->
            val key = keySelector(value)
            if (previousKey != key) {
                previousKey = keySelector(value)
                emit(value)
            }
        }
    } // TODO suspend in lambda, inliner 1.3.30