package week13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.FileInputStream
import java.io.FileOutputStream

class TestByteStream {

    @Test
    fun byteStreamTest() {
        val input = FileInputStream("sample.txt") // "hello world" 를 포함하는 텍스트 문서

        input.use {
            val bytes = input.readAllBytes() // 바이트로 읽는다
            println(String(bytes))
        }

        val output = FileOutputStream("output.txt")
        output.use {
            it.write("write test".toByteArray())
        }
    }

}

