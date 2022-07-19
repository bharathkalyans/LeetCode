package learning;

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
