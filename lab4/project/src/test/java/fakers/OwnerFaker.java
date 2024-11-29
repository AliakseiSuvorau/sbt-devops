package fakers;

import com.github.javafaker.Faker;
import model.Owner;

import java.util.concurrent.ThreadLocalRandom;

public class OwnerFaker {
    public static Owner fake() {
        Faker faker = new Faker();
        var name =  faker.name();

        return new Owner(name.firstName(), name.lastName(), ThreadLocalRandom.current().nextInt());
    }
}
