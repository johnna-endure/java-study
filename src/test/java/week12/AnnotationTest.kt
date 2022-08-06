package week12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.reflect.full.findAnnotations


class AnnotationTest {
    @Test
    fun test() {
        println(TargetClass::class.findAnnotations(A::class)) // [@week12.A()]
    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@MustBeDocumented
annotation class A

@A
class TargetClass
