import fakers.CarFaker;
import fakers.OwnerFaker;
import model.Car;
import model.GarageImpl;
import model.Owner;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class GarageTest {
    @Test
    @SuppressWarnings("unchecked")
    public void testCarCreate() {
        GarageImpl garage = new GarageImpl();

        int numOfCars = 10;

        for (int i = 0; i < numOfCars; i++) {
            Owner owner = OwnerFaker.fake();
            Car car = CarFaker.fake(owner.getId());

            assertDoesNotThrow(() -> garage.addNewCar(car, owner), "Method addNewCar should not throw exception");
        }

        int numOfCarsActual = 0;
        int numOfOwnersActual = 0;
        try {
            Field carsField = GarageImpl.class.getDeclaredField("cars");
            carsField.setAccessible(true);
            var a = (Map<Integer, Car>) carsField.get(garage);
            numOfCarsActual = (a).size();


            Field ownersField = GarageImpl.class.getDeclaredField("owners");
            ownersField.setAccessible(true);
            numOfOwnersActual = ((Map<Integer, Owner>) ownersField.get(garage)).size();
        } catch (Exception ignored) {}

        assertEquals(numOfCars, numOfCarsActual);
        assertEquals(numOfCars, numOfOwnersActual);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRemoveCar() {
        GarageImpl garage = new GarageImpl();
        List<Integer> carIds = new ArrayList<>();
        Set<Integer> owners = new HashSet<>();

        int numOfCars = 10;

        for (int i = 0; i < numOfCars; i++) {
            Owner owner = OwnerFaker.fake();
            owners.add(owner.getId());
            Car car = CarFaker.fake(owner.getId());
            carIds.add(car.getCarId());

            garage.addNewCar(car, owner);
        }
        int expectedOwnersNum = owners.size();

        for (int id : carIds) {
            assertDoesNotThrow(() -> garage.removeCar(id), "Method removeCar should not throw exception");
        }

        int numOfCarsActual = 0;
        int numOfOwnersActual = 0;
        int numOfCarsByOwnersActual = 0;
        int numOfCarsByBrandActual = 0;
        int numOfCarsByVelocityActual = 0;
        int numOfCarsByPowerActual = 0;
        try {
            Field carsField = GarageImpl.class.getDeclaredField("cars");
            carsField.setAccessible(true);
            numOfCarsActual = ((Map<Integer, Car>) carsField.get(garage)).size();

            Field ownersField = GarageImpl.class.getDeclaredField("owners");
            ownersField.setAccessible(true);
            numOfOwnersActual = ((Map<Integer, Owner>) ownersField.get(garage)).size();

            Field carsByOwnersField = GarageImpl.class.getDeclaredField("carsByOwners");
            carsByOwnersField.setAccessible(true);
            var carsByOwners = (Map<Owner, List<Car>>) carsByOwnersField.get(garage);
            for (var owner: carsByOwners.keySet()) {
                numOfCarsByOwnersActual += carsByOwners.get(owner).size();
            }

            Field carsByBrandsField = GarageImpl.class.getDeclaredField("carsByBrands");
            carsByBrandsField.setAccessible(true);
            var carsByBrands = (Map<String, List<Car>>) carsByBrandsField.get(garage);
            for (var brand: carsByBrands.keySet()) {
                numOfCarsByBrandActual += carsByBrands.get(brand).size();
            }

            Field carsByVelocityField = GarageImpl.class.getDeclaredField("carsByVelocity");
            carsByVelocityField.setAccessible(true);
            var carsByVelocity = (SortedMap<Integer, List<Car>>) carsByVelocityField.get(garage);
            for (var velocity: carsByVelocity.keySet()) {
                numOfCarsByVelocityActual += carsByVelocity.get(velocity).size();
            }

            Field carsByPowerField = GarageImpl.class.getDeclaredField("carsByPower");
            carsByPowerField.setAccessible(true);
            var carsByPower = (SortedMap<Integer, List<Car>>) carsByPowerField.get(garage);
            for (var power: carsByPower.keySet()) {
                numOfCarsByPowerActual += carsByPower.get(power).size();
            }
        } catch (Exception ignored) {}

        assertEquals(0, numOfCarsActual);
        assertEquals(expectedOwnersNum, numOfOwnersActual);
        assertEquals(0, numOfCarsByOwnersActual);
        assertEquals(0, numOfCarsByBrandActual);
        assertEquals(0, numOfCarsByVelocityActual);
        assertEquals(0, numOfCarsByPowerActual);
    }

    @Test
    public void testMeanCarNumber() {
        GarageImpl garage = new GarageImpl();

        int totalNumOfCars = 0;
        int numOfOwners = 10;

        for (int i = 0; i < numOfOwners; i++) {
            Owner owner = OwnerFaker.fake();

            int randomNumOfCars = ThreadLocalRandom.current().nextInt(1, 10);
            totalNumOfCars += randomNumOfCars;

            for (int j = 0; j < randomNumOfCars; j++) {
                Car car = CarFaker.fake(owner.getId());
                garage.addNewCar(car, owner);
            }
        }

        int expectedMeanCarNumber = totalNumOfCars / numOfOwners;
        int actualMeanCarNumber = garage.meanCarNumberForEachOwner();

        assertEquals(expectedMeanCarNumber, actualMeanCarNumber);
    }

    @Test
    public void testMeanOwnersAgeOfCarBrand() {
        GarageImpl garage = new GarageImpl();

        int numOfCars = 10;
        Map<String, List<Integer>> agesByBrand = new HashMap<>();

        for (int i = 0; i < numOfCars; i++) {
            Owner owner = OwnerFaker.fake();
            Car car = CarFaker.fake(owner.getId());

            if (agesByBrand.containsKey(car.getBrand())) {
                agesByBrand.get(car.getBrand()).add(owner.getAge());
            } else {
                agesByBrand.put(car.getBrand(), new ArrayList<>());
                agesByBrand.get(car.getBrand()).add(owner.getAge());
            }

            garage.addNewCar(car, owner);
        }

        for (var brand : agesByBrand.keySet()) {
            int expectedMeanOwnerAge = agesByBrand.get(brand).stream().mapToInt(Integer::intValue).sum() / agesByBrand.get(brand).size();
            int actualMeanOwnerAge = garage.meanOwnersAgeOfCarBrand(brand);

            assertEquals(expectedMeanOwnerAge, actualMeanOwnerAge);
        }
    }

    @Test
    public void testCarsOfOwner() {
        GarageImpl garage = new GarageImpl();
        List<Owner> listOfOwners = new ArrayList<>();
        Map<Owner, List<Car>> carsByOwners = new HashMap<>();

        int numOfOwners = 10;

        for (int i = 0; i < numOfOwners; i++) {
            Owner owner = OwnerFaker.fake();
            listOfOwners.add(owner);
            carsByOwners.put(owner, new ArrayList<>());

            int randomNumOfCars = ThreadLocalRandom.current().nextInt(1, 10);

            for (int j = 0; j < randomNumOfCars; j++) {
                Car car = CarFaker.fake(owner.getId());
                carsByOwners.get(owner).add(car);

                garage.addNewCar(car, owner);
            }
        }

        for (var owner : listOfOwners) {
            var expectedCarsOfOwner = carsByOwners.get(owner);
            var actualCarsOfOwner = garage.allCarsOfOwner(owner);

            assertEquals(expectedCarsOfOwner, actualCarsOfOwner);
        }
    }

    @Test
    public void testCarsWithPowerMoreThan() {
        GarageImpl garage = new GarageImpl();

        int powerThreshold = ThreadLocalRandom.current().nextInt(100, 200);
        int numOfCars = 10;
        List<Car> listOfCars = new ArrayList<>();

        for (int i = 0; i < numOfCars; i++) {
            Owner owner = OwnerFaker.fake();
            Car car = CarFaker.fake(owner.getId());

            if (car.getPower() > powerThreshold) {
                listOfCars.add(car);
            }

            garage.addNewCar(car, owner);
        }

        var actualListOfCars = garage.carsWithPowerMoreThan(powerThreshold);

        assertEquals(listOfCars.size(), actualListOfCars.size());
        for (var car : listOfCars) {
            assertTrue(actualListOfCars.contains(car));
        }
    }

    @Test
    public void testAllCarsOfBrand() {
        GarageImpl garage = new GarageImpl();
        List<String> listOfBrands = new ArrayList<>();
        Map<String, List<Car>> carsByBrands = new HashMap<>();

        int numOfOwners = 10;

        for (int i = 0; i < numOfOwners; i++) {
            Owner owner = OwnerFaker.fake();

            int randomNumOfCars = ThreadLocalRandom.current().nextInt(1, 10);

            for (int j = 0; j < randomNumOfCars; j++) {
                Car car = CarFaker.fake(owner.getId());

                if (!listOfBrands.contains(car.getBrand())) {
                    listOfBrands.add(car.getBrand());
                }

                if (!carsByBrands.containsKey(car.getBrand())) {
                    carsByBrands.put(car.getBrand(), new ArrayList<>());
                    carsByBrands.get(car.getBrand()).add(car);
                } else {
                    carsByBrands.get(car.getBrand()).add(car);
                }

                garage.addNewCar(car, owner);
            }
        }

        for (var brand : listOfBrands) {
            var expectedCarsOfBrand = carsByBrands.get(brand);
            var actualCarsOfBrand = garage.allCarsOfBrand(brand);

            assertEquals(expectedCarsOfBrand, actualCarsOfBrand);
        }
    }

    @Test
    public void testTopThreeFastestCars() {
        GarageImpl garage = new GarageImpl();

        int numOfCars = 10;
        Map<Integer, Integer> velocitiesToNumOfCars = new TreeMap<>();

        for (int i = 0; i < numOfCars; i++) {
            Owner owner = OwnerFaker.fake();
            Car car = CarFaker.fake(owner.getId());

            if (!velocitiesToNumOfCars.containsKey(-car.getMaxVelocity())) {
                velocitiesToNumOfCars.put(-car.getMaxVelocity(), 1);
            } else {
                velocitiesToNumOfCars.put(-car.getMaxVelocity(), velocitiesToNumOfCars.get(-car.getMaxVelocity()) + 1);
            }

            garage.addNewCar(car, owner);
        }

        var topThreeCars = garage.topThreeCarsByMaxVelocity();

        assertEquals(3, topThreeCars.size());

        int numOfUncheckedCars = 3;
        for (int velocity : velocitiesToNumOfCars.keySet()) {
            if (velocitiesToNumOfCars.get(velocity) >= numOfCars) {
                for (var car : topThreeCars) {
                    assertEquals(car.getMaxVelocity(), velocity);
                }
            }
            numOfUncheckedCars = numOfUncheckedCars - velocitiesToNumOfCars.get(velocity);

            if (numOfUncheckedCars <= 0) {
                break;
            }
        }
    }

    @Test
    public void testUniqueOwners() {
        GarageImpl garage = new GarageImpl();
        List<Owner> ownersList = new ArrayList<>();

        int numOfCars = 10;

        for (int i = 0; i < numOfCars; i++) {
            Owner owner = OwnerFaker.fake();
            Car car = CarFaker.fake(owner.getId());

            if (!ownersList.contains(owner)) {
                ownersList.add(owner);
            }

            garage.addNewCar(car, owner);
        }

        var actualOwnerList = garage.allCarsUniqueOwners();

        assertEquals(ownersList.size(), actualOwnerList.size());
        for (var owner : actualOwnerList) {
            assertTrue(ownersList.contains(owner));
        }
    }
}
