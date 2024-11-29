package model;

import java.util.*;

public class GarageImpl implements Garage {
    private final Map<Integer, Owner> owners = new HashMap<>();
    private final Map<Integer, Car> cars = new TreeMap<>();
    private final Map<Owner, List<Car>> carsByOwners = new HashMap<>();
    private final Map<String, List<Car>> carsByBrands = new TreeMap<>();
    private final SortedMap<Integer, List<Car>> carsByVelocity = new TreeMap<>(Comparator.reverseOrder());
    private final SortedMap<Integer, List<Car>> carsByPower = new TreeMap<>(Comparator.reverseOrder());

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return owners.values();
    }

    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        List<Car> topThreeCars = new ArrayList<>();

        for (List<Car> cars : carsByVelocity.values()) {
            for (Car car : cars) {
                if (topThreeCars.size() == 3) {
                    return topThreeCars;
                }
                topThreeCars.add(car);
            }
        }

        return topThreeCars;
    }

    @Override
    public Collection<Car> allCarsOfBrand(String brand) {
        return carsByBrands.get(brand);
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        var cars = new ArrayList<Car>();

        for (int carPower : carsByPower.keySet()) {
            if (carPower > power) {
                cars.addAll(carsByPower.get(carPower));
            } else {
                return cars;
            }
        }

        return cars;
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        return carsByOwners.get(owner);
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        var carsOfBrand = carsByBrands.get(brand);
        Set<Integer> uniqueOwners = new HashSet<>();

        int totalAge = 0;
        for (Car car : carsOfBrand) {
            if (uniqueOwners.add(car.getOwnerId())) {
                totalAge += owners.get(car.getOwnerId()).getAge();
            }
        }

        if (uniqueOwners.isEmpty()) {
            return 0;
        }
        return totalAge / uniqueOwners.size();
    }

    @Override
    public int meanCarNumberForEachOwner() {
        if (owners.isEmpty()) {
            return 0;
        }

        return cars.size() / owners.size();
    }

    @Override
    public Car removeCar(int carId) {
        Car car = cars.remove(carId);
        if (car == null) {
            return null;
        }

        Owner owner = owners.get(car.getOwnerId());
        carsByOwners.remove(owner);

        int velocity = car.getMaxVelocity();
        carsByVelocity.get(velocity).remove(car);

        String brand = car.getBrand();
        carsByBrands.get(brand).remove(car);

        Integer power = car.getPower();
        carsByPower.get(power).remove(car);

        return car;
    }

    @Override
    public void addNewCar(Car car, Owner owner) {
        if (car == null || owner == null) {
            return;
        }

        if (car.getOwnerId() != owner.getId()) {
            throw new IllegalArgumentException("Car owner id mismatch");
        }

        owners.putIfAbsent(owner.getId(), owner);

        cars.put(car.getCarId(), car);

        // Add car by owner
        carsByOwners.computeIfAbsent(owner, nothing -> new ArrayList<>());
        carsByOwners.get(owner).add(car);

        // Add car by brand
        var brand = car.getBrand();
        carsByBrands.computeIfAbsent(brand, nothing -> new ArrayList<>());
        carsByBrands.get(brand).add(car);

        // Add car by velocity
        var velocity = car.getMaxVelocity();
        carsByVelocity.computeIfAbsent(velocity, nothing -> new ArrayList<>());
        carsByVelocity.get(velocity).add(car);

        // Add car by power
        carsByPower.computeIfAbsent(car.getPower(), nothing -> new ArrayList<>());
        carsByPower.get(car.getPower()).add(car);
    }
}
