package week8

interface KotlinInterface {
    val message: String

    fun bar()
    fun foo() {

    }
}

class KotlinClass(override val message: String) : KotlinInterface {

    override fun bar() {
        bar()
        TODO("Not yet implemented")
    }

}

interface Named {
    val name: String
    fun hello()
}

interface Person : Named {
    val firstName: String
    val lastName: String
    override val name: String get() = "$firstName $lastName"


    override fun hello() {
        TODO("Not yet implemented")
    }

    //    override val name: String get() = "$firstName $lastName"
}

data class Employee(
//    override val name: String,
    override val firstName: String,
    override val lastName: String
//    override val lastName: String
) : Person {
    companion object Factory {
        fun staticHello() {
            println("static hello")
        }
    }
}

interface Factory<T> {
    fun create(): T
}

class MyClass {
    companion object : Factory<MyClass> {
        override fun create(): MyClass {
            println("created...")
            return MyClass()
        }
    }
}

interface KotlinInterfaceHasPrivateMethod {
    private fun foo() {
        println("invoked private method")
    }
}


fun main() {
    MyClass.create()
}

