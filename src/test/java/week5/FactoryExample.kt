package week5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FactoryExample {

    @Test
    fun factoryTest() {
        // given
        val user: User = Admin("cws", Role.ADMIN)

        // when
        val ret = UserFactory.createUser(user.role)

        // then
        assertThat(ret.name).isEqualTo("cws")
        assertThat(ret.role).isEqualTo(Role.ADMIN)
    }

}

interface User {
    val name: String
    val role: Role
}

object UserFactory {
    fun createUser(role: Role): User {
        return when (role) {
            Role.ADMIN -> Admin("cws", Role.ADMIN)
            Role.CUSTOMER -> Customer("cws", Role.CUSTOMER)
            else -> throw IllegalArgumentException("role : ${role.name}")
        }
    }
}

class Admin(
    override val name: String,
    override val role: Role
) : User {
}

class Customer(
    override val name: String,
    override val role: Role
) : User {
}

enum class Role {
    ADMIN,
    CUSTOMER
}