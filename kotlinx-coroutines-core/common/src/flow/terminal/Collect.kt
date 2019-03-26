@file:Suppress("UNCHECKED_CAST")

package kotlinx.coroutines.flow.terminal

import kotlinx.coroutines.flow.*

/**
 * Terminal flow operator that collects the given flow with a provided [action].
 * If any exception occurs during collect or in the provided flow, this exception is rethrown from this method.
 *
 * Example of use:
 * ```
 * val flow = getMyEvents()
 * try {
 *   flow.collect { value ->
 *     println("Received $value")
 *   }
 *   println("My events are consumed successfully")
 * } catch (e: Throwable) {
 *   println("Exception from the flow: $e")
 * }
 * ```
 */
public suspend fun <T : Any> Flow<T>.collect(action: suspend (value: T) -> Unit): Unit =
    collect(object : FlowCollector<T> {
        override suspend fun emit(value: T) = action(value)
    })
// TODO inliner 1.3.30
// TODO evaluate performance of operators written on top of tranform when inliner is fixed
