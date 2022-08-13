# 13주차 과제: I/O

## 목표

자바의 Input과 Ontput에 대해 학습하세요.

## Java IO : Stream[ ByteStream, CharacterStream, BufferedStream]

## Stream 이란

input source 와 output destination 사이의 데이터 흐름을 나타내는 용어.  
동기식 방식. 데이터가 전달되는 동안 해당 스레드는 블로킹된다.

## ByteStream

모든 ByteStream 는 InputStream, OutputStream 을 상속받는다.

![](image/week13/byteStream.gif)
출처 : https://docs.oracle.com/javase/tutorial/essential/io/bytestreams.html

위 그림에서처럼 read는 byte 배열을 결과로 리턴하고, write 메서드는 byte 배열을 인수로 받는다.  
문자열 데이터를 주고 받는 경우, Character Stream 을 사용하는게 더 낫다.

```kotlin
@Test
fun byteStreamTest() {
    val input = FileInputStream("sample.txt") // "hello world" 를 포함하는 텍스트 문서

    input.use {
        val bytes = input.readAllBytes()
        println(String(bytes))
    }

    val output = FileOutputStream("output.txt")
    output.use {
        it.write("write test".toByteArray())
    }
}
```

## Character Stream

모든 문자열 스트림은 Reader 나 Writer 를 상속한다.  
자바는 유니코드 컨벤션을 이용해 문자열 값을 저장한다. 문자열 스트림은 자동으로 유니코드 셋을 지역 문자열 셋으로 변환해준다.

문자열 스트림은 기본적으로 바이트 스트림의 wrapper 클래스다. 문자열 관련해 편의 기능들이 추가된 클래스이다.

## Java NIO : Buffer, Channel, Selector ...

## 표준 스트림 (System.in, System.out, System.err)

## 파일 읽고 쓰기