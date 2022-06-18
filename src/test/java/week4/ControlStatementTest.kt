package week4

import org.junit.jupiter.api.Test

class ControlStatementTest {
    @Test
    fun helloTest() {
        val user = Admin("cws")

        fun printType(user: User) {
            print("type is ${user.myType()}")
        }

        printType(user)
    }

    @Test
    fun narrowInterestTest() {


    }

}

sealed interface User {
    val name: String
    fun myType(): String
}
class Admin(override val name: String) : User {
    private val type:String  = "admin"

    override fun myType(): String {
        return type
    }
}

class Customer(
    override val name: String
) : User {

    private val type:String  = "customer"
    private val logined: Boolean = false

    override fun myType(): String {
        return type
    }
}