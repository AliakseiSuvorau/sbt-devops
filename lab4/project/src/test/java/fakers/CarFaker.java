package fakers;

import model.Car;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CarFaker {
    private static int nextId = 0;
    private static final List<String> brands = Arrays.asList(
            "Toyota",
            "Honda",
            "Ford",
            "Chevrolet",
            "BMW",
            "Mercedes-Benz",
            "Audi",
            "Nissan",
            "Hyundai",
            "Kia",
            "Tesla",
            "Volkswagen",
            "Subaru",
            "Volvo",
            "Lexus",
            "Porsche",
            "Maserati",
            "Ferrari",
            "Lamborghini",
            "Jeep"
    );
    private static final Map<String, List<String>> modelsByBrands = new HashMap<String, List<String>>() {{
        put("Toyota", Arrays.asList("Corolla", "Camry", "RAV4", "Highlander", "Prius", "Supra"));
        put("Honda", Arrays.asList("Civic", "Accord", "CR-V", "Pilot", "HR-V", "Fit"));
        put("Ford", Arrays.asList("Mustang", "F-150", "Explorer", "Escape", "Bronco", "Fusion"));
        put("Chevrolet", Arrays.asList("Silverado", "Camaro", "Malibu", "Equinox", "Suburban", "Corvette"));
        put("BMW", Arrays.asList("3 Series", "5 Series", "X3", "X5", "i4", "M3"));
        put("Mercedes-Benz", Arrays.asList("C-Class", "E-Class", "S-Class", "GLC", "GLE", "AMG GT"));
        put("Audi", Arrays.asList("A3", "A4", "Q5", "Q7", "A8", "R8"));
        put("Nissan", Arrays.asList("Altima", "Maxima", "Rogue", "Murano", "Frontier", "GT-R"));
        put("Hyundai", Arrays.asList("Elantra", "Sonata", "Tucson", "Santa Fe", "Kona", "Palisade"));
        put("Kia", Arrays.asList("Optima", "Sorento", "Sportage", "Stinger", "Telluride", "Soul"));
        put("Tesla", Arrays.asList("Model S", "Model 3", "Model X", "Model Y", "Cybertruck"));
        put("Volkswagen", Arrays.asList("Golf", "Passat", "Tiguan", "Jetta", "Atlas", "ID.4"));
        put("Subaru", Arrays.asList("Outback", "Forester", "WRX", "Impreza", "Crosstrek", "Ascent"));
        put("Volvo", Arrays.asList("XC40", "XC60", "XC90", "S60", "S90", "V60"));
        put("Lexus", Arrays.asList("ES", "RX", "NX", "IS", "LC", "GX"));
        put("Porsche", Arrays.asList("911", "Cayenne", "Panamera", "Macan", "Taycan", "718 Boxster"));
        put("Maserati", Arrays.asList("Ghibli", "Levante", "Quattroporte", "GranTurismo"));
        put("Ferrari", Arrays.asList("488 GTB", "F8 Tributo", "Portofino", "Roma", "SF90 Stradale"));
        put("Lamborghini", Arrays.asList("Huracán", "Aventador", "Urus"));
        put("Jeep", Arrays.asList("Wrangler", "Grand Cherokee", "Compass", "Renegade", "Gladiator"));
    }};

    public static Car fake(int ownerId) {
        int randomBrandIdx = ThreadLocalRandom.current().nextInt(0, brands.size());
        String randomBrand = brands.get(randomBrandIdx);

        int numOfModels = modelsByBrands.get(randomBrand).size();
        int randomModelIdx = ThreadLocalRandom.current().nextInt(0, numOfModels);
        String randomModel = modelsByBrands.get(randomBrand).get(randomModelIdx);

        int randomVelocity = ThreadLocalRandom.current().nextInt(100, 200);
        int randomPower = ThreadLocalRandom.current().nextInt(100, 200);

        return new Car(nextId++, randomBrand, randomModel, randomVelocity, randomPower, ownerId);
    }
}
