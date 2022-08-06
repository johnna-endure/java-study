package week11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class EnumTest {


    @Test
    fun test() {
        TestEnum.A.greeting()
        TestEnum.B.greeting()
    }

    @Test
    fun builtInMethodTest() {
        assertThat(TestEnum.A.name).isEqualTo("A") // enum 상수의 이름
        assertThat(TestEnum.A.ordinal).isEqualTo(0) // enum 상수의 선언된 순서
        assertThat(TestEnum.A.declaringClass).isEqualTo(TestEnum::class.java) // class type
        assertThat(TestEnum.A.compareTo(TestEnum.A)).isEqualTo(0) // ordinal을 이용해 비교
        assertThat(TestEnum.A.compareTo(TestEnum.B)).isEqualTo(-1)
        assertThat(TestEnum.A.equals(TestEnum.A)).isTrue() // equals 비교
        assertThat(TestEnum.A == TestEnum.A).isTrue() // == 연산자로 비교 가능
        assertThat(TestEnum.A == TestEnum.B).isFalse() // == 연산자로 비교 가능

        TestEnum.values().forEach { println(it.name) }
        assertThat(TestEnum.valueOf("A")).isEqualTo(TestEnum.A)
    }

    @Test
    fun enumSet() {
        val set = EnumSet.range(TestEnum.B, TestEnum.C) // ordinal 을 사용해 범위 지정 가능
//        val set = EnumSet.allOf(TestEnum::class.java) // 해당 enum 클래스에 선언된 모든 enum이 포함된다.
        println(set) // [B,C]
        set.add(TestEnum.A)
        println(set) // [A,B,C]

        assertThat(set.contains(TestEnum.A)).isTrue
    }
}


enum class TestEnum {
    A {
        override fun greeting() {
            println("hello")
        }
    },
    B {
        override fun greeting() {
            println("안녕하세요")
        }

    },
    C {
        override fun greeting() {
            println("봉주르")
        }

    };

    abstract fun greeting(): Unit

}