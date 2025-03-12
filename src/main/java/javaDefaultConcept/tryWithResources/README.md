# try - with - resources

---

## try-catch
try-with-resources에서 resources는 파일에 접근하여 뭔가를 시도할때 사용할 수 있는 문법이다.

일반적인 try-catch 문법을 사용하여 파일에 접근할때 발생하는 문제는 다음과 같다.

```java
try {
    BufferedReader reader = new BufferedReader(new FileReader("./"));
    //파일 읽어오는 로직
} catch (IOException e){
    throw new RuntimeException(e);
} finally {
    reader.close(); 
}
```

위에서 발생하는 문제는 reader.close() 자체가 실패할 수도 있다.
따라서 file.close()도 try-catch를 사용하여 시도해야 안전하다.

```java
BufferedReader reader = null;
try {
    reader = new BufferedReader(new FileReader("./"))
    //파일 읽어오는 로직
} catch (IOException e){
    throw new RuntimeException(e);
} finally {
    if (reader != null) {
        try {
            reader.close();    
        } catch (IOException e) {
            throw new RuntimeException(e);    
        }
    }
}
```
위 처럼 finally 부분에서 reader가 null인지 아닌지 판별 후 다시 try-catch로 reader.close()를 해야 안전하다.
이중 try-catch의 경우 코드의 가독성이 매우 떨어진다.

## try-with-resources
위 try-catch의 대안으로 사용 방법은 다음과 같다.

```java
try (BufferedReader reader = new BufferedReader(new FileReader("..."))) {
    //파일 읽어오는 로직
} catch (IOException e) {
    throw new RuntimeException(e);    
}
```
try 부분에 소괄호안에 파일 관련 객체를 선언하고 try {} 중괄호 사이에 관련 로직을 집어넣어 주면 된다.
try 부분의 중괄호`{}`를 벗어나면 try 소괄호`()` 내에 있던 파일 관련 객체를 자동으로 close를 해준다.

파일의 close()를 자동으로 선언하기 때문에 finally를 사용하지 않아도 된다.

`;로 여러 파일 객체를 집어넣을 수도 있다.`