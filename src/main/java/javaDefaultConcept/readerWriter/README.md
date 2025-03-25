# Reader & Writer

---

Reader와 Writer클래스는 데이터의 입출력 역할을 하는 클래스로

바이트단위로 데이터를 다룬다.

그 중에서도 FilerReader, FileWriter의 경우 문자열에 특화되어 바이트단위의 데이터를 문자로 번역한다.

InputStreamReader, OutputStreamWriter는 바이트단위에 데이터를 다룬다.

---

## Reader

Reader클래스를 상속하여 사용할수있는 클래스는 크게 다음과 같이 3개가 있다.

1. InputStreamReader
2. FileReader
3. BufferedReader


### InputStreamReader
InputStreamReader는 Reader클래스를 직접 상속한 클래스로 바이트단위의 데이터를 지정한 인코딩형식으로 디코딩할 수 있다.

생성자의 인자로 inputStream 클래스를 받는다.

- InputStream클래스 : 파일을 읽어 바이트단위로 읽어오는 역할
- FileInputStream클래스 : InputStream을 상속한 클래스
- InputStreamReader클래스 : InputStream으로 읽어온 데이터를 지정한 인코딩 방식으로 디코딩하는 역할

```java
InputStreamReader reader = new InputStreamReader(new FileInputStream("../"), "UTF-8");
```
**적은 양과 간단한 읽기 작업에 적합하며 한 글자씩 읽을 수 있다.**

---

### FileReader
FileReader는 위의 InputStreamReader를 상속한 클래스로 인코딩이 OS 설정값으로 세팅되어있다.

```java
import java.io.FileReader;

FileReader reader = new FileReader("../");
```

**기본 인코딩으로 인코딩된 문자열과 적은 양, 간단한 읽기 작업에 적합하며 한 글자씩 읽을 수 있다.**

---

### BufferedReader
BufferedReader는 Reader클래스를 직접 상속한 클래스로 버퍼라는 공간을 활용하여 효과적인 읽기 성능을 제공한다.

BufferedReader의 생성자 인자로는 Reader클래스를 인자로 받기 때문에 Reader클래스를 상속한 InputStreamReader를 인자로 받을 수 있다.

디스크 -> OS -> 자바 순으로 읽어올때 다른 Reader는 매 요청마다 디스크에게 직접 요청을 하면서 리소스가 낭비된다.

반대로 BufferedReader는 **read(), readLine()**같은 메소드 사용 시 **8192(기본값)글자를 추가로 읽어와 버퍼에 저장한다.**

이후 다시 읽기 요청을 하거나 추가적으로 읽어올때 버퍼에 저장한 값을 사용하면서 디스크에 직접적인 요청하는 것을 방지하고 효율성을 높일 수 있다.

**따라서 여러번 읽기 요청을 하거나, 대용량 읽기 처리의 경우 적합하다.**

**Reader클래스를 상속한 자식 클래스**
- InputStreamReader
- FileReader

**InputStreamReader 클래스를 직접 상속한 자식 클래스**
- FileReader



---

## Writer

Writer클래스를 상속하여 사용할수있는 클래스는 크게 다음과 같이 3개가 있다.

1. OutputStreamWriter
2. FileWriter
3. BufferedWriter

### OutputStreamWriter
OutputStreamWriter은 Writer클래스를 직접 상속한 클래스로 문자열을 바이트단위로 인코딩한다.

생성자의 인자로 OutputStream 클래스를 받는다.

- OutputStream클래스 : 바이트단위의 데이터를 파일에 쓰기 작업을 하는 역할
- FileOutputStream클래스: OutputStream클래스를 상속한 클래스
- OutputStreamWriter클래스 : 쓰기 작업할 할 문자열 데이터를 바이트단위로 인코딩하는 역할

```java
OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("../"), "UTF-8");
```

**적은 양과 간단한 쓰기 작업에 적합하며 문자열 단위로 작업이 가능하다.**

---

### FileWriter
FileWriter는 위의 OutputStreamWriter를 상속한 클래스로 인코딩이 OS 설정값으로 세팅되어있다.

```java
FileWriter writer = new FileWriter("../");
```

**기본 인코딩으로 인코딩된 문자열과 적은 양, 간단한 쓰기 작업에 적합하며 문자열 단위로 작업이 가능하다.**

---

### BufferedWriter
BufferedWriter는 Writer클래스를 직접 상속한 클래스로 버퍼라는 공간을 활용하여 효과적인 쓰기 성능을 제공한다.

BufferedWriter의 생성자 인자로는 Writer클래스를 인자로 받기 때문에 OutputStreamWriter를 인자로 받을 수 있다.

BufferedWriter는 **write() 메소드 사용 시 바로 디스크에 저장하지 않고 **8192(기본값)공간을 가진 버퍼에 저장한다.**

이후 flush()나 close()메소드 호출 시 버퍼에 저장된 데이터를 일괄적으로 저장하기 때문에 디스크에 직접적인 접근 빈도를 줄여서 효율성을 높인다.

**따라서 여러번 쓰기 요청을 하거나, 대용량 쓰기 처리의 경우 적합하다.**

**Writer클래스를 상속한 자식 클래스**
- OutputStreamWriter
- FileWriter

**OutputStreamReader 클래스를 직접 상속한 자식 클래스**
- FileWriter




