package learning;

import java.util.*;
import java.util.stream.Collectors;

public class JavaStreams {

    public static void main(String[] args) {
        List<Info> al = new LinkedList<>();
        al.add(new Info(23, "Bharath", Gender.MALE));
        al.add(new Info(123, "Dalai Lama", Gender.MALE));
        al.add(new Info(19, "Ananth", Gender.MALE));
        al.add(new Info(43, "Raghu Dixit", Gender.MALE));
        al.add(new Info(28, "Selena Gomex", Gender.FEMALE));
        al.add(new Info(28, "Taylor Swift", Gender.FEMALE));
        al.add(new Info(82, "Lisbon Eria", Gender.OTHER));



//        al.forEach(System.out::println);

        List<Info> eligibleVoters = al.stream()
                .filter(ele -> ele.getAge() >= 18)
                .collect(Collectors.toList());

        long count = al.stream()
                .filter(obj -> obj.getGender() == Gender.MALE)
                .count();

        //FILTER
        List<Info> males = al.stream()
                .filter(info -> info.getGender().equals(Gender.MALE))
                .collect(Collectors.toList());

//        males.forEach(System.out::println);


        //SORT
        List<Info> sortedAge = al.stream()
                .sorted(Comparator.comparing(Info::getAge).reversed())
                .collect(Collectors.toList());

//        sortedAge.forEach(System.out::println);

        //ALL MATCH
        boolean allMatch = al.stream()
                .allMatch(info -> info.getName().length() > 10);

        boolean allMatchAge = al.stream()
                .allMatch(info -> info.getAge() > 10);

//        System.out.println(allMatch);
//        System.out.println(allMatchAge);

        //NONE MATCH
        boolean nonMatch = al.stream()
                .noneMatch(info -> info.getAge() < 5);

//        System.out.println(nonMatch);

        //ANY MATCH
        boolean anyMatch = al.stream()
                .anyMatch(info -> info.getAge() > 200);

//        System.out.println(anyMatch);

        Optional<Info> maxAge = al.stream()
                .max(Comparator.comparing(Info::getAge));

//        System.out.println(maxAge);

        Optional<Info> minAge = al.stream()
                .min(Comparator.comparing(Info::getGender).thenComparing(Info::getAge));
//        System.out.println(minAge);

        al.stream()
                .min(Comparator.comparing(Info::getAge));
//                .ifPresent(System.out::println);

        //GROUP
        Map<Gender, List<Info>> genderListMap = al.stream()
                .collect(Collectors.groupingBy(Info::getGender));

//        genderListMap.forEach((gender, infos) -> {
//            System.out.println(gender + " ");
//            infos.forEach(System.out::println);
//            System.out.println();
//        });

        //Print Oldest Female Person in the Database.

        List<Info> collect = al.stream()
                .filter(info -> info.getGender().equals(Gender.MALE))
                .sorted(Comparator.comparing(Info::getAge).reversed())
//                .map(info -> info.getAge())//Type cast to info if used.
//                .max(Comparator.comparing(learning.Info::getAge))
                .collect(Collectors.toList());

        collect.forEach(System.out::println);



    }
}
