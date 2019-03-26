package kotlinx.coroutines.flow.operators

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.builders.*
import kotlinx.coroutines.flow.terminal.*

/**
 * Merges given sequence of flows into a single flow with no guarantees on the order.
 */
public fun <T: Any> Iterable<Flow<T>>.merge(): Flow<T> = asFlow().flatMap { it }
