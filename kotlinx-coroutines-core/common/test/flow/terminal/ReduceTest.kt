/*
 * Copyright 2016-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package kotlinx.coroutines.flow.terminal

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.builders.*
import kotlin.test.*

class ReduceTest : TestBase() {
    @Test
    fun testReduce() = runTest {
        val flow = flow {
            emit(1)
            emit(2)
        }

        val result = flow.reduce { value, acc -> value + acc }
        assertEquals(3, result)
    }

    @Test
    fun testEmptyReduce() = runTest {
        val flow = flowOf<Int>()
        assertFailsWith<UnsupportedOperationException> { flow.reduce { value, acc -> value + acc } }
    }

    @Test
    @Ignore // K/N fails here
    fun testErrorCancelsUpstream() = runTest {
        val latch = Channel<Unit>()
        val flow = flow {
            coroutineScope {
                launch {
                    latch.send(Unit)
                    expect(3)
                    hang { expect(5) }
                }
                expect(2)
                emit(1)
                emit(2)
            }
        }

        expect(1)
        assertFailsWith<TestException> {
            flow.reduce { _, _ ->
                latch.receive()
                expect(4)
                throw TestException()
            }
        }
        finish(6)
    }
}
