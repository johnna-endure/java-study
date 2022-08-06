# 12주차 과제: 애노테이션

## 목표

자바의 애노테이션에 대해 학습하세요.

## 애노테이션 정의하는 방법

```kotlin
@Target(
    AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
@MustBeDocumented
annotation class A 
```

### @Target

```kotlin
public enum class AnnotationTarget {
    /** Class, interface or object, annotation class is also included */
    CLASS,

    /** Annotation class only */
    ANNOTATION_CLASS,

    /** Generic type parameter */
    TYPE_PARAMETER,

    /** Property */
    PROPERTY,

    /** Field, including property's backing field */
    FIELD,

    /** Local variable */
    LOCAL_VARIABLE,

    /** Value parameter of a function or a constructor */
    VALUE_PARAMETER,

    /** Constructor only (primary or secondary) */
    CONSTRUCTOR,

    /** Function (constructors are not included) */
    FUNCTION,

    /** Property getter only */
    PROPERTY_GETTER,

    /** Property setter only */
    PROPERTY_SETTER,

    /** Type usage */
    TYPE,

    /** Any expression */
    EXPRESSION,

    /** File */
    FILE,

    /** Type alias */
    @SinceKotlin("1.1")
    TYPEALIAS
}
```

애너테이션을 달 수 있는 요소를 지정

### @Retention

애너테이션을 컴파일된 클래스에 저장하는지 여부, 리플렉션을 통한 접근을 허용할지 여부를 지정
SOURCE : 컴파일된 클래스에 포함하지 않음
BINARY : 컴파일된 클래스에 포, 리플렉션을 통해 접근 불가
RUNTIME : 컴파일된 클래스에 포, 리플렉션을 통해 접근 가능

### @Repeatable

단일 요소에 동일한 애너테이션을 여러 번 사용할 수 있는지 여부

## @MustBeDocumented

해당 요소가 공개 API임을 표시하고, 문서가 포함되어야 함을 알리는 애너테이션

## 간단한 테스트

코틀린 리플렉션을 사용하기 위해 아래 의존성을 추가해야한다.

```kotlin
//gradle
implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")
```

```kotlin

class AnnotationTest {
    @Test
    fun test() {
        println(TargetClass::class.findAnnotations(A::class)) // [@week12.A()]
    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class A

@A
class TargetClass


```

## 애너테이션 프로세서

AbstractProcessor 클래스를 상속해 지원 애너테이션을 지정하고,
해당 애너테이션에 작업을 정의할 수 있다.

