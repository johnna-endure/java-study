# Java Study 1주차

## 목표
자바 소스 파일을 JVM으로 실행하는 과정 이해하기.


## JVM이란 무엇인가?
자바 바이트코드를 해석하고 실행하는 소프트웨어.
JVM으로 인해 OS에 독릭적으로 코드를 작성할 수 있다.

## JVM 구성 요소
![](https://www.freecodecamp.org/news/content/images/size/w1000/2021/01/image-39.png)
 출처 : https://www.freecodecamp.org/news/jvm-tutorial-java-virtual-machine-architecture-explained-for-beginners/

### Class Loader
![](https://www.freecodecamp.org/news/content/images/size/w1000/2021/01/image-40.png)
출처 : https://www.freecodecamp.org/news/jvm-tutorial-java-virtual-machine-architecture-explained-for-beginners/

.java 파일을 컴파일하면 .class 파일이 생성됩니다.
이 .class 파일을 실행하려고 할때, 클래스 로더는 메인 메모리에 클래스를 올리게 됩니다.

클래스 로더의 동작에는 세가지 과정이 있음.
Loading, Linking, Initialization

#### Loading
.class 파일을 클래스를 메모리에 로딩하는 과정.
각 타입의 Class 인스턴스를 생성. 
위의 클래스 로더는 java 11 기준에 맞지 않다.

현재는,
- Bootstrap class loader
- Platform class loader 
- System class loader 
이렇게 바뀜.

더 자세한 사항은 ClassLoader 클래스의 주석을 찾아보면 알 수 있다.

#### Linking 
메모리에 클래스를 적재 후에, 관련있는 클래스나 인터페이스들을 연결시키는 과정
Verify, Prepare, Resolve 세 가지 과정이 존재한다.
- Verify  
.class 의 파일 내 코드가 구조적으로 올바른지 체크.
검증에 실패할 경우, `VerifyException` 을 던진다. 
- Preparation  
클래스나 인터페이스의 static 필드들을 메모리에 할당하고, 기본값으로 초기화.(boolean의 경우 false, int의 경우 0)  
- Resolution  
이 단계에서 symbolic reference가 direct reference로 바뀐다.
  - symbolic reference  
  실제 메모리 위치를 가리키지 않는 논리적 참조

#### Initialization
클래스 로딩의 마지막 단계이며, 클래스나 인터페이스의 초기화 메서드를 실행하는 단계.  
- 클래스의 생성자 호출
- static block 영역 실행
- static 변수에 값 할당

*static 변수 할당에 대해서*
```
private static final boolean cute = true;
```
준비 단계에서 기본값을 할당한 후, 할당한 값이 있다면 초기화 단계에서 값 할당.

***
### Runtime Data Area
![](https://www.freecodecamp.org/news/content/images/size/w1000/2021/01/image-32.png)
출처 : https://www.freecodecamp.org/news/jvm-tutorial-java-virtual-machine-architecture-explained-for-beginners/

#### Method Area
런타임 상수풀, 멤버 필드, 메서드 메타 데이터, 생성자 등의 모든 클래스 레벨의 데이터가 이 영역에 저장됨.
이 영역의 메모리가 부족할 경우, JVM은 `OutOfMemoryException`을 던진다.  

![](https://blog.jamesdbloom.com/images_2013_11_17_17_56/JVM_Internal_Architecture_small.png)
출처 : https://blog.jamesdbloom.com/JVMInternals.html#:~:text=When%20a%20Java%20class%20is,to%20a%20physical%20memory%20location  

- Runtime Constant Pool
클래스 파일은 모든 symbolic reference를 각자의 constant pool에 저장한다.  
Runtime Constant Pool은 이런 클래스 파일들의 constant pool에 저장된 정보를 저장하기 위한 Method Area 내의 영역이다.

#### Heap Area
모든 객체 인스턴스, 인스턴스 변수들이 저장되는 영역

#### Stack Area
![](https://blog.jamesdbloom.com/images_2013_11_17_17_56/JVM_Internal_Architecture_small.png)
출처 : https://blog.jamesdbloom.com/JVMInternals.html#:~:text=When%20a%20Java%20class%20is,to%20a%20physical%20memory%20location

생성된 스레드는 이 영역에서 각자의 영역을 가진다.  
모든 로컬 변수, 메서드 콜, 결과값들이 이 영역에 저장된다.

모든 메서드 콜은 Frame 이라는 단위로 스택에 저장된다.  
메서드가 종료되면 프레임도 사라진다.  

프레임은 3가지 파트로 구성돼있다.  
- Local Variables  
로컬 변수가 저장되는 영역
- Operand Stack  
메서드에서 중간 연산들이 저장되는 영역  
- Frame Data  
메서드에 관계된 참조값들을 저장하는 영역

#### Program Counter Register
JVM의 바이트코드 set을 이곳에 보관한다.

*JVM 인스트럭션*
참고 : https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html

#### Native Method Stack
C, C++로 작성된 네이티브 메서드를 지원하기 위한 영역
***
### Execution Engine
클래스로더가 클래스를 메인 메모리에 로드하고 Runtime data area 저장된 상세 정보들을 이용해이 영역에서 프로그램을 실행시킨다.

![](https://www.freecodecamp.org/news/content/images/2021/01/image-33.png)
출처 : https://www.freecodecamp.org/news/jvm-tutorial-java-virtual-machine-architecture-explained-for-beginners/

* Interpreter  
바이트코드를 한줄씩 실행한다. 느리다는 단점이 있다.

* Garbage Collector
참조가 없는 객체들을 힙 메모리에서 제거
## 컴파일 하는 방법
javac 명령어를 이용.
### javac options  
- -classpath or -cp : 클래스패스(어떤 경로에서 클래스파일을 찾을지) 지정 가능
- -encoding : 소스파일 캐릭터 인코딩 지정
- -nowarn : warning 메세지 끄기
- -source : 소스파일 호환 버전 제공
- -target : VM 버전 지정

## 바이트코드란 무엇인가
특정 하드웨어가 아닌 가상 머신에서 돌아가는 실행 프로그램을 위한 이진 표현법.
자바에선 컴파일러에 의해 생성된 .class 파일의 코드.

## JIT 컴파일러란 무엇이며 어떻게 동작하는지
* JIT Compiler

![](https://velog.velcdn.com/images%2Fmooh2jj%2Fpost%2F8d7e4300-a085-42f1-9546-54367104df96%2Fimage.png)
출처 : https://images.app.goo.gl/LsXuRMLSYmfSDWzQ9

전체 바이트코드를 컴파일하고, 반복되는 기계코드를 미리 PC 레지스터에 캐시해두고 사용한다.
참고 : https://velog.io/@mooh2jj/JIT-%EC%BB%B4%ED%8C%8C%EC%9D%BC%EB%9F%AC%EB%9E%80

## JDK와 JRE 차이
JRE : 자바를 실행하는데 포커스를 둔 번들. 
JDK : 자바를 사용하기 위해 필요한 모든 기능을 포함. 



