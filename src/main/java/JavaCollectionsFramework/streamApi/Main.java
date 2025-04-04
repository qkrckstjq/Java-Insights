package JavaCollectionsFramework.streamApi;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Set<String> nameSet = new HashSet<>(Set.of("박찬섭", "김잡이", "신쟈는", "신지드"));
//        nameSet.forEach(System.out::println);


        List<Person> nameList = new ArrayList<>(List.of(
                new Person("박찬섭", 25),
                new Person("김잡이", 22),
                new Person("신나은", 15),
                new Person("최정규", 56),
                new Person("정승기", 36),
                new Person("박찬비", 27)
                ));

        nameList.forEach((person) -> System.out.println(person.getName()));
        System.out.println("-------------------------------------------");
        nameList.parallelStream().forEach((person) -> System.out.println(person.getName()));

        //25살 이상 "박"으로 시작하는 인물 리스트 사전순으로
        List<Person> normalList = method1(nameList);
        List<Person> streamList = method2(nameList);

//        System.out.println("normal-list");
//        printList(normalList);
//
//        System.out.println("stream-list");
//        printList(streamList);
    }

    public static List<Person> method1(List<Person> nameList) {
        List<Person> result = new ArrayList<>();
        for(Person person : nameList) {
            if (person.getAge() >= 25 && person.getName().startsWith("박")) {
                result.add(person);
            }
        }
        result.sort(Comparator.comparing(Person::getName));
        return result;
    }

    public static List<Person> method2(List<Person> nameList) {
        return nameList.stream()
                .filter((person) -> person.getAge() >= 25 && person.getName().startsWith("박"))
                .sorted(Comparator.comparing(Person::getName))
                .toList();
    }

    public static void printList(List<Person> nameList) {
        nameList.forEach((person) -> {
                    System.out.println(person.getName() + person.getAge());
                });
    }
}
