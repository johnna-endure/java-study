package week5

import org.junit.jupiter.api.Test

//val param = "global"
//fun print() {
//    println(this.param) // 컴파일 에러
//}
//
//val lambda = {
//    this.param // 컴파일 에러
//}

class ExamplA(val param: String = "hello") {
    fun print() {
        println(this.param)
    }

    val printFunc = {
        println(this.param)
    }
}

class KotlinThisTest {

    @Test
    fun test() {
        val a = ExamplA()

        a.print()
        a.printFunc.invoke()
    }
}
