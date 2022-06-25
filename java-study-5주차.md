`# 5주차 과제: 클래스

## 목표

자바의 class에 대해 학습하세요.

## 클래스를 정의하는 방법

### 자바

아래와 같은 형식으로 클래스 정의

```java
class Example {
    String fieldA;
    String fieldB;

    Example() {

    }

    public void method1() {
    }

    public void method2() {
    }
}
```

### 코틀린

```kotlin

class Example(
    fieldA: String,
    fieldB: String
) {
    fun method1() {

    }

    fun method2() {

    }
}
```

위는 주생성자를 통해 클래스를 정의한 코드입니다.
코틀린에서는 주생성자 내에 멤버 필드를 정의하고, 멤버 필드를 클래스 정의 내에서 사용할 수 있습니다.

## 객체를 만드는 방법

### 객체 생성할 때 팩토리를 사용하자

무작정 팩토리를 사용할 필요는 없습니다.  
객체 생성이 어떤 값에 의해서 동적으로 생성될 때 사용하면 유용합니다.

### 팩토리 예제 코드

```kotlin

class FactoryExample {

    @Test
    fun factoryTest() {
        // given
        // 여기서는 user 객체가 Admin 타입라는걸 알지만,
        // user 타입이 다른 모듈이나 메서드에서 User 타입으로 반환된 값이라고 상상해보자.
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
```

## 생성자 정의하는 방법

코틀린에서는 코드의 간결성을 위해 보통 주생성자를 통해 클래스를 정의합니다.

```kotlin
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
```

아래 주석처럼 추가적으로 생성자를 정의할 수 있습니다.  
하지만 코틀린에서는 파라미터에 기본값을 정해줄 수 있기 때문에 주생성자를 이용해 위 방식으로 정의하는게 더 간결합니다.

## this 키워드 이해하기

자바에서 this는 객체 자신을 가리키는 키워드입니다.

보통 클래스 내의 함수에서의 this는 클래스의 인스턴스를 가리킵니다.

### Quiz - 람다의 this

```java
public class ThisTest {
    String message = "global";

    @Test
    public void thisTest() {
        Runnable printRunnable = () -> {
            System.out.println(this.message); // Q1
        };

//        Runnable printRunnable = new Runnable() {
//            @Override
//            public void run() {
//                this.message // Q2
//            }
//        };
        var example = new Example();
        example.print(printRunnable);
    }
}


class Example {
    String message = "inner";

    public void print(Runnable runnable) {
        runnable.run();
    }
}
```
