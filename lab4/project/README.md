Java Task 1
-----
## API

- One can create an owner with:
  - `name`: `String`,
  - `last_name`: `String`,
  - `age`: `Integer`

- One can create a car with:
  - `id`: `Integer`,
  - `brand_name`: `String`,
  - `model_name`: `String`, 
  - `max_velocity`: `Integer`,
  - `power`: `Integer`, 
  - `owner_id`: `Integer`

- Example:
```java
Owner owner = new Owner("John", "Snow", 35);
int ownerId = owner.getId();

Car car = new Car(1, "Toyota", "Corolla", 200, 150, ownerId);
```

- Garage implements these methods:
  - `allCarsUniqueOwners` - get all owners without duplicates
  - `topThreeCarsByMaxVelocity` - get top-3 cars with the highest speed
  - `allCarsOfBrand` - get all cars of a specific brand. Takes in a string with brand name
  - `carsWithPowerMoreThan` - get all cars with power more than a threshold. Takes in an integer as a threshold
  - `allCarsOfOwner` - get all cars of a specific owner. Takes in Owner object
  - `meanOwnersAgeOfCarBrand` - get mean age of all owners of a specific brand. Takes in a string with a brand name
  - `meanCarNumberForEachOwner` - get mean number of cars possessed by each owner
  - `removeCar` - remove car from garage by id
  - `addNewCar` - add new car to garage. Takes in Car and Owner objects

## Compile & run

```bash
mvn compile
java -jar target/hw1-garage-1.0-SNAPSHOT.jar
```
Result:
```
[INFO] Scanning for projects...
[INFO]
[INFO] -----------------------< org.example:hw1-garage >-----------------------
[INFO] Building hw1-garage 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- clean:3.2.0:clean (default-clean) @ hw1-garage ---
[INFO] Deleting D:\Alex\mipt\sem_7\sbertech\java\sbt-java\hw1-garage\target
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ hw1-garage ---
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ hw1-garage ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 5 source files with javac [debug target 21] to target\classes
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ hw1-garage ---
[INFO] skip non existing resourceDirectory D:\Alex\mipt\sem_7\sbertech\java\sbt-java\hw1-garage\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ hw1-garage ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 3 source files with javac [debug target 21] to target\test-classes
[INFO]
[INFO] --- surefire:3.2.5:test (default-test) @ hw1-garage ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running GarageTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.607 s -- in GarageTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jar:3.4.0:jar (default-jar) @ hw1-garage ---
[INFO] Building jar: D:\Alex\mipt\sem_7\sbertech\java\sbt-java\hw1-garage\target\hw1-garage-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.339 s
[INFO] Finished at: 2024-09-22T11:34:37+03:00
[INFO] ------------------------------------------------------------------------
```


## Tests

```bash
mvn test
```
Result:
```
[INFO] Scanning for projects...
[INFO]
[INFO] -----------------------< org.example:hw1-garage >-----------------------
[INFO] Building hw1-garage 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ hw1-garage ---
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ hw1-garage ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ hw1-garage ---
[INFO] skip non existing resourceDirectory D:\Alex\mipt\sem_7\sbertech\java\sbt-java\hw1-garage\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ hw1-garage ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- surefire:3.2.5:test (default-test) @ hw1-garage ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running GarageTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.455 s -- in GarageTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.304 s
[INFO] Finished at: 2024-09-22T11:36:51+03:00
[INFO] ------------------------------------------------------------------------
```