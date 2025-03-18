# GC (Garbage Collection)

---

자바에서 힙(heap) 영역에 객체들이 저장이 되는데 이 힙 영역에서 주기적으로 사용되지 않는 (참조되지 않는) 객체들을 삭제하는 기능을 말한다.

이 사용되지 않는 객체를 삭제하는 과정에서 GC를 담당하는 스레드를 제외하고 나머지 스레드들은 모두 멈추게 된다. `Stop-The-World`

GC는 Mark-And-Sweep-Compact 방식을 사용한다.

- Mark: 사용 중인 객체를 식별(마킹)
- Sweep: 마킹되지 않은 객체 제거
- Compact: 단편화된 메모리를 정리하여 연속된 공간으로 압축

이때 GC는 힙 영역중에서 두 가지 __Young, Old__ 영역으로 나누어 메모리를 관리한다.

---

## Young

새롭게 생성되는 객체들이 저장되고 관리되는 공간이다.

새롭게 생성되는 객체는 __Young__ 영역중에서 __Eden__ 의 영역에 생성된다.

이 Eden의 영역이 가득차게 되면 Minor GC를 발생시키고 생존한 객체들은 Survivor0 또는 Survivor1의 영역으로 복사되고 사용되지 않는 객체들은 삭제된다.

이후 Eden이 다시 가득 차면 반드시 Minor GC가 발생하고 Survivor의 영역이 가득 찬다면 일부 객체들이 Old 영역으로 조기 승격될 수도 있다.

이때 Survivor 영역 중 한 곳은 전부 비워지게 되며 나머지 Survivor 영역으로 이동(복사)된다.

이 이동 과정이 반복되어 생존 횟수가 일정 횟수(기본 15회)를 넘는다면 Old 영역으로 승격된다.

---

## Old

Young 영역에서 승격된 객체들이 저장되고 관리되는 공간이다.

Old 영역또한 가득 찬다면 Major GC가 발생하여 사용되지 않는 객체들이 정리된다.

Young 영역에서의 Minor GC보다 조금 더 긴 STW가 발생한다.

---

GC의 대상이 아닌 객체들도 존재하는데

static, 자바 8 이후의 default 메소드등이 예시이다.

__Minor GC는 Copying GC 알고리즘 사용으로__
1. 객체를 Survivor로 복사하는 과정에서 해당 객체가 사용되는 객체인지 아닌지 판단
2. 생존한 객체 survivor로 복사

이 2번 복사되는 과정에서 자연스레 단편화된 객체들이 차례대로 복사되면서 압축화까지 이루어지고 빠르다.

__Major GC는 Mark and Sweep Compact 방식__
1. GC Root 부터 시작해 도달 가능한 객체를 탐색하고 마킹한다.
2. 마킹되지 않은 객체들 삭제
3. 2번 과정에서 단편화된 메모리들을 압축시켜줌

Old 영역에서 발생하는 GC로 객체 탐색, 삭제, 압축화 과정을 거치기 때문에 시간이 상대적으로 느리다.

---
# GC 알고리즘 종류

GC는 알고리즘에 따라 작동하는 방식이 다르다.

기본적으로 young/old의 메모리 영역으로 분류하여 관리하는 방식은 비슷하지만

이 young/old의 영역의 크기를 고정하고 실행되느냐, 런타임중에 동적으로 변경되느냐의 차이와
처리과정에서 STD가 발생하는 기존 방식과 처리하는 과정과 실행 과정이 동시에 실행되어 STD가 발생하지 않는 병렬 방식 즉 Region방식으로 나뉜다.

## 기존 방식 Young/Old 영역의 크기 고정
이 방식에 해당하는 GC는 young/old 영역의 메모리 크기를 고정한 상태로 작동한다.

Young 영역에서는 eden, survivor 공간으로 분리하고 이 young 영역에서 오래 생존한 객체는 old 영역으로 승격되는 과정은 동일하다.

### Serial GC
싱글 스레드로 진행된다.

작은 크기의 힙에서 사용하기 적합하다.

---

### Parallel GC
Serial GC의 멀티 스레드 버전이다.

Serial보다는 많은 양의 처리가 가능하다.

---

## CMS
Young 영역에서의 GC는 Parallel GC와 동일하게 멀티 스레드 처리지만,

old 영역세어는 멀티 스레드로 처리한다. compact과정을 생략하기에 메모리 파편화가 생기지만,
concurrent 방식으로 STD를 발생시키지 않고 GC처리를 병렬적으로 진행한다.

---

## Region GC Young/Old 영역의 동적 할당
기존 방식과 다르게 Young/Old의 영역의 크기를 정하지 않고,
새롭게 생성되는 객체를 담당하는 region이 young 영역이 되고 이 영역안에서도 eden과 survivor역할을 하는 region이 각자 동적으로 할당된다.

이 young영역이 오랫동안 지속되면 해당 region이 old영역의 역할을 하게된다.

Region GC의 경우 객체들의 생존 상황에 맞게 young/old 영역의 비율을 조절하기 때문에
기존 방식보다 더 효율적인 GC처리를 지향한다.

### G1 GC
Young region에서의 GC는 복사 GC(region 기반)를 통해 선별한다.`(std 발생함)`

Old 영역에서는 mixed GC를 통해 정리되는데 Young region에서 GC가 일어날때 일부 old 영역도 정리되는 것이다.

young region에서의 GC과정에서 old region의 일부도 같이 정리하기 때문에 최대한 Full GC를 지연시킨다.

---

### ZGC 와 shenandoah GC
이 두 가지 GC는 다른 region GC들과 달리 young/old의 구분이 없다.

모두 concurrent한 진행으로 실행과 GC처리를 동시에 진행한다.
GC 방식은 MSC의 방식이다.

두 가지 GC의 작동의 차이점은 참조값 처리의 차이이다.

ZGC는 객체를 이동`(복사)`시키고 참조값을 수정하지 않는다.
대신 load barrier라는 기술을 사용하여 이동된 객체로 접근한다면 알맞는 참조값으로 자동으로 업데이트한다.
STD가 거의 발생하지 않는다.

zhenandoah GC는 객체를 이동시키고 참조값을 즉시 새롭게 수정한다.
즉시 업데이트 하는 과정에서 STD가 일부 발생할 수 있다.

두 차이점으로 ZGC는 초지연 환경`(게임, 금융...)`에 적합하고 Zhenandoah GC는 일반 웹 서버에 적합하다.

| 이름                  | young                                | STD 발생 여부 | old                                               | STD 발생 여부 | 적합한 환경                                          |
|---------------------|--------------------------------------|-----------|---------------------------------------------------|-----------|-------------------------------------------------|
| Serial              | Copying GC                           | O         | MSC GC                                            | O         | 작은 규모의 힙 환경                                     |
| Parallel            | Copying GC(멀티 스레드)                   | O         | MSC GC(멀티 스레드)                                    | O         | 높은 throughPut이 필요한 환경                           |
| CMS                 | Copying GC(멀티 스레드)                   | O         | MS GC(Mark 단계만 멀티 스레드)                            | X         | parallel GC보다 STD가 적게 발생해야하는 경우                 |
| G1 GC               | region기반의 Copying GC(멀티 스레드)         | O         | Mixed GC(Young region GC 실행 시 일부 old region을 msc) | X         | 대부분의 환경에서 탁월                                    |
| ZGC & Zhenandoah GC | Young/Old 구별 없이 Concurrent 환경에서의 MSC | X         | Young/Old 구별 없이 Concurrent 환경에서의 MSC | X         | ZGC의 경우 초저지연 환경, zhenandoah는 대규모 트래픽 웹 사이트에서 적합 |









