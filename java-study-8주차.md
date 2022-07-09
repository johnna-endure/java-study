~~# 8주자 과제: 인터페이스

## 목표

자바의 인터페이스에 대해 학습하세요.

## 인터페이스 정의하는 방법

### kotlin interface

java와 동일하게 interface 키워드를 사용해서 인터페이스를 정의할 수 있습니다.

```kotlin
interface KotlinInterface {
    val message: String

    fun bar()
    fun foo() {

    }
}
```

메서드를 선언하면서 구현부를 작성하지 않을 경우 추상 메서드가 되고, 자식 클래스에서 반드시 구현해야 합니다.

## 인터페이스 구현하는 방법

오버라이드 대상 메서드 앞에는 override 라는 키워드가 붙습니다.

```kotlin

class KotlinClass(override val message: String) : KotlinInterface {
    //    override val messagee: String = "hello"
    override fun bar() {
        bar()
        TODO("Not yet implemented")
    }

}

```

## 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

```kotlin

var c: KotlinInterface = KotlinClass("hello")

```

다형성이 필요한 경우 인터페이스 레퍼런스를 이용해 구현체를 사용

## 인터페이스 상속

```kotlin
interface Named {
    val name: String

    fun hello()
}

interface Person : Named {
    val firstName: String
    val lastName: String
//    override val name: String get() = "$firstName $lastName"

    override fun hello() {
        print("hello")
    }
}

data class Employee(
    override val name: String,
    override val firstName: String
//    override val lastName: String,
) : Person {

}

```

인터페이스는 다른 인터페이스를 상속할 수 있습니다.  
인터페이스를 상속할 경우, 부모 인터페이스 속성을 필요할 경우 오버라이드할 수 있습니다.  
오버라이드 하는 경우, 위처럼 Person을 구현하는 Employee에서 오버라이드된 속성을 다시 오버라이드하지 않아도 됩니다. (필요하다면 다시 오버라이딩이 가능합니다.)

## 인터페이스의 기본 메소드 (Default Method), 자바 8

```kotlin
interface Hello {
    fun hello(message: String) {
        print(message)
    }
}
```

코틀린에서는 default 키워드없이 함수에 구현부를 제공하면 자동으로 default 함수가 됩니다.

## 인터페이스의 static 메소드, 자바 8

```kotlin
data class Employee(
//    override val name: String,
    override val firstName: String,
    override val lastName: String
//    override val lastName: String
) : Person {
    companion object {
        fun staticHello() {
            println("static hello")
        }
    }
}
```

자바에서 인터페이스에 있는 static 함수를 코틀린에서 구현하려면 companion object를 사용합니다.

```kotlin
class MyClass {
    companion object Factory {
        fun create() {
            print("create")
        }
    }
}

fun main() {
    MyClass.Factory.create()
//    MyClass.create() // 같은 결과
}
```

companion object 키워드 뒤에 이름을 붙여줄 수 있습니다.  
이름을 명시하더라도 이름은 생략이 가능합니다.

```kotlin
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

fun main() {
    MyClass.create()
}

```

참고로 companion object는 인터페이스를 상속하므로 위와 같은 형태도 가능합니다.

## 인터페이스의 private 메소드, 자바 9~~

```kotlin

interface KotlinInterfaceHasPrivateMethod {
    private fun foo() {
        println("invoked private method")
    }
}

```

코틀린 인터페이스에서도 private 메서드가 가능합니다.  
private 메서드의 경우, 반드시 메서드 구현부를 제공해줘야 합니다.