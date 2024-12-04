package model;

public class Owner {
    private final int id;
    private final String name;
    private final String lastName;
    private final int age;
    private static int nextId = 0;

    public Owner(String name, String lastName, int age) {
        this.id = nextId++;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }
}
