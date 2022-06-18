package week5

import org.junit.jupiter.api.Test

class ConstructorExample {

    @Test
    fun createPerson() {
        Person("cws")
    }
}

class Person(
    val name: String,
    val age: Int = 0
)

//class Person(
//    val name: String,
//    val age: Int
//) {
//    constructor(name: String) : this(name, 0)
//}


