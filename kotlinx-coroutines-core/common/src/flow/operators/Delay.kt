package kotlinx.coroutines.flow.operators

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.builders.*
import kotlinx.coroutines.flow.terminal.*

/**
 * Delays the emission of values from this flow for the given [timeMillis].
 */
public fun <T : Any> Flow<T>.delayFlow(timeMillis: Long): Flow<T> = flow {
    delay(timeMillis)
    collect {
        emit(it)
    }
}

/**
 * Delays each element emitted by the given flow for the given [timeMillis].
 */
public fun <T : Any> Flow<T>.delayEach(timeMillis: Long): Flow<T> = flow {
    collect {
        delay(timeMillis)
        emit(it)
    }
}
