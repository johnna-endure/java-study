package week9

import org.junit.jupiter.api.Test

class TryCatchTest {

    @Test
    fun test() {
        throwException()
    }

    fun throwException() {
        throw java.lang.Exception()
    }

}
