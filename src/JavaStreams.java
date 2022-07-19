import java.util.*;
import java.util.stream.Collectors;

enum Gender {
    MALE, FEMALE, OTHER
}

class Info {
    private int age;
    private String name;
    private Gender gender;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "{" + this.name + " : " +
                this.getAge() + " : " +
                this.getGender() + "}";
    }

    Info(int age, String name, Gender sex) {
        this.age = age;
        this.name = name;
        this.gender = sex;
    }
}

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

        genderListMap.forEach((gender, infos) -> {
            System.out.print(gender + " ");
            infos.forEach(System.out::print);
            System.out.println();
        });
    }
}
