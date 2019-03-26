package kotlinx.coroutines.flow.operators

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.builders.unsafeFlow as flow // hehe
import kotlinx.coroutines.flow.terminal.*

/**
 * Applies [transformer] function to each value of the given flow.
 * [transformer] is a generic function hat may transform emitted element, skip it or emit it multiple times.
 *
 * This operator is useless by itself, but can be used as a building block of user-specific operators:
 * ```
 * fun Flow<Int>.skipOddAndDuplicateEven(): Flow<Int> = transform { value ->
 *   if (value % 2 == 0) { // Emit only even values, but twice
 *     emit(value)
 *     emit(value)
 *   } // Do nothing if odd
 * }
 * ```
 */
public fun <T : Any, R : Any> Flow<T>.transform(@BuilderInference transformer: suspend FlowCollector<R>.(value: T) -> Unit): Flow<R> {
    // TODO inliner 1.3.30
    // TODO evaluate performance of operators written on top of tranform when inliner is fixed
    return flow {
        collect { value ->
            transformer(value)
        }
    }
}
