# equals, hashCode

---

모든 클래스는 Object 클래스를 자동으로 상속받는다.

모든 Object 클래스는 Objects에서 제공하는 equals를 통해 서로 비교할수있다.

Objects에서 제공하는 equals는 각 클래스의 참조값을 비교하기 때문에 다음과 같은 상황에서는 False가 나온다.

```java
public class Person {
    private final String name;
    
    public Person(String name) {
        this.name = name;
    }
}

public static void main(String[] args) {
    Person p1 = new Person("hi");
    Person p2 = new Person("hi");

    System.out.println(Objects.equals(p1, p2));
}

//Objects.equals의 코드
public static boolean equals(Object a, Object b) {
    return (a == b) || (a != null && a.equals(b));
}

//Object의 equals의 코드
public boolean equals(Object obj) {
    return (this == obj);
}
```
따라서 논리적으로 p1과 p2는 서로 다르다.

하지만 자체적으로 name이 일치하면 서로 같다고 수정하고 싶다면 Object의 equals를 오버라이드 해야한다.

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    return (obj instanceof Person p && Objects.equals(p.name, this.name));
}
```
위 처럼 수정하면 더 이상 참조값끼리의 비교가 아닌 커스텀하게 수정한 name끼리의 비교를 통해 논리를 따진다.

---

## hashCode

하지만 위대로 equals로만 끝난다면 Collections에서 제공하는 HashMap과 같은 자료구조에서는 여전히 원하는 논리를 따지기 힘들다.

```java
public static void main(String[] args) {
    Set<Person> set = new HashSet<>();
    Person p1 = new Person("hi");
    Person p2 = new Person("hi");

    set.add(p1);
    if (set.contains(p2)) {
        System.out.println("set안에 p2 있음");
    } else {
        System.out.println("set안에 p2 없음");
    }
}

//HashSet이 제공하는 contains는 다음과 같다.
public boolean contains(Object o) {
    return map.containsKey(o);
}

//map의 containsKey
public boolean containsKey(Object key) {
    return getNode(key) != null;
}

//getNode의 코드
final Node<K,V> getNode(Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n, hash; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & (hash = hash(key))]) != null) {
        if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}

//hash(key)의 hash 코드
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```
HashSet에 값을 저장할때 객체를 해쉬화하여 키로 저장한다.
이때 기본적인 hashCode는 객체의 주소값을 해쉬화 하기 때문에 같은 값이 들어간 객체더라도 서로 다른 객체기 때문에 찾지 못한다.

하지만 이 hashCode자체를 오버라이딩하여 원하는 값을 지정하여 해쉬화하여 저장하면

커스텀하게 작동하는 해쉬코드로 저장하고 찾는것도 커스텀한 해쉬코드로 값을 찾는다.

```java
@Override
public int hashCode() {
    return Objects.hashCode(this.name);
}
```
위 처럼 hashCode를 오버라이딩하여 name을 해쉬화하여 키로 잡는다면 원하는대로 작동하게 수정할 수 있다.



