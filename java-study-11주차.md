# 11주차 과제: Enum

## 목표

자바의 열거형에 대해 학습하세요.

## enum 정의하는 방법

### enum 이란?

상수 집합을 정의하기 위한 data type

### 정의하는 방법

```kotlin

enum class Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
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

    };
    //C 만약 구현하지 않을 경우 컴파일 에러 발생 

    abstract fun greeting(): Unit // 추상 메서드 정의 가능
}

```

## enum이 제공하는 메소드 (values()와 valueOf())

```kotlin
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
```

## java.lang.Enum

모든 enum class의 베이스가 되는 클래스

```kotlin
public abstract class Enum<E : Enum<E>>(name: String, ordinal: Int) : Comparable<E> {
    companion object {}

    public final val name: String

    public final val ordinal: Int

    public override final fun compareTo(other: E): Int

    protected final fun clone(): Any

    public override final fun equals(other: Any?): Boolean
    public override final fun hashCode(): Int
    public override fun toString(): String
}
```

## EnumSet

```kotlin
enum class TestEnum {
    A,
    B,
    C;
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

```