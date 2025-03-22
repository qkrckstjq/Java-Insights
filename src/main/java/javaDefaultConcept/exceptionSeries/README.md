# Checked & UnChecked Exception

---

둘 모두 치명적인 오류는 아닌 예외처리이다.

## Checked Exception

개발자가 확인 가능한 예외로 **컴파일 과정에서 체크**할 수 있는 예외이다.

**컴파일러가 예외 처리를 강제한다.**

Checked Exception의 경우 개발자가 모두 체크해야 한다.

개발자가 확인 가능하기 때문에 Checked Exception은 모두 try-catch로 감싸진 형태이다.

RunTimeException을 상속하지 않는 예외들이다.

- IOException: 파일 또는 네트워크 연결에서 읽기 또는 쓰기와 같은 입력/출력 작업과 관련된 오류

- SQLException: 데이터베이스 액세스 및 쿼리와 관련된 오류

- ClassNotFoundException: 동적으로 클래스 로드와 관련된 오류

- InterruptedException: 스레드 중단 및 동기화와 관련된 오류의 경우

---

## UnChecked Exception

개발자가 미리 체크하기 힘들거나 하지 못하는 예외들로 **런타임 과정에서 발생하는 예외**이다.

컴파일러가 예외 처리를 강제하지 않는다.

해당 예외들은 모두 RunTimeException을 상속한 예외들이다.

try-catch로 확인 가능하지만, NPE와 같은 예외들처럼 확인불가능한 예외도 존재한다.

- NullPointerException: 객체가 null인 상태에서 메서드나 필드를 접근하려 할 때 발생.

- ArrayIndexOutOfBoundsException: 배열의 인덱스가 범위를 벗어났을 때 발생.

- IllegalArgumentException: 메서드가 잘못된 인자를 받았을 때 발생.

- ArithmeticException: 잘못된 산술 연산(예: 0으로 나누기)으로 발생.

---

### Checked Exception:

- 컴파일 과정에서 확인 가능한 예외.

- 반드시 예외 처리가 필요 (try-catch 또는 throws).

- 예: IOException, SQLException.

### UnChecked Exception:

- 런타임 과정에서 발생하는 예외.

- 예외 처리가 강제되지 않음.

- 예: NullPointerException, ArrayIndexOutOfBoundsException. 