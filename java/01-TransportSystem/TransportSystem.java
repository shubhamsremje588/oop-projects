public class TransportSystem {

    public static void main( String[] args ) {

        Car car = new Car( "Toyota", "Camry", 120, FuelType.PETROL, 4 );
        Truck truck = new Truck( "Volvo", "FH16", 90, FuelType.DIESEL, 20000.0 );

        car.display();
        truck.display();

        System.out.println( car.getLocation() );
        System.out.println( truck.getLocation() );

        System.out.println( truck.serviceStatus() );

        FleetManager.registerVehicle();
        FleetManager.registerVehicle();

        System.out.println( FleetManager.COMPANY );
        System.out.println( FleetManager.getTotalVehicles() );

        Vehicle v = new Car( "Toyota", "Camry", 120, FuelType.PETROL, 4 );
        System.out.println( v.vehicleType() );

    }

}

enum FuelType {

    PETROL( "Petrol" ),
    DIESEL( "Diesel" ),
    ELECTRIC( "Electric" ),
    HYBRID( "Hybrid" );

    private final String displayName;

    FuelType( String displayName ) {

        this.displayName = displayName;

    }

    public String getDisplayName() {

        return displayName;

    }
}

interface Trackable {

    String getLocation();

}

interface Serviceable {

    String serviceStatus();

}

abstract class Vehicle {

    private final String brand;
    private final String model;
    private final int speed;
    private final FuelType fuelType;

    public Vehicle( String brand, String model, int speed, FuelType fuelType ) {

        this.brand = brand;
        this.model = model;
        this.speed = speed;
        this.fuelType = fuelType;

    }

    abstract String vehicleType();

    void display() {

        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Speed: " + speed);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Vehicle Type: " + vehicleType());

    }
}

class Car extends Vehicle implements Trackable {

    private final int numberOfDoors;

    public Car( String brand, String model, int speed, FuelType fuelType, int numberOfDoors ) {

        super( brand, model, speed, fuelType );
        this.numberOfDoors = numberOfDoors;

    }

    @Override
    public String getLocation() {

        return "Car located at GPS: 12.34, 56.78";

    }

    @Override
    public String vehicleType() {

        return "Car";

    }

    @Override
    public void display() {

        super.display();
        System.out.println("Number of Doors: " + numberOfDoors);

    }

}

class Truck extends Vehicle implements Trackable, Serviceable {

    private final double payloadCapacity;

    public Truck( String brand, String model, int speed, FuelType fuelType, double payloadCapacity ) {

        super( brand, model, speed, fuelType );
        this.payloadCapacity = payloadCapacity;

    }

    @Override
    public String getLocation() {

        return "Truck located at GPS: 12.34, 56.78";

    }

    @Override
    public String serviceStatus() {

        return "Truck service due in 500km";

    }


    @Override
    public String vehicleType() {

        return "Truck";

    }

    @Override
    public void display() {

        super.display();
        System.out.println("Payload Capacity: " + payloadCapacity);

    }

}

class FleetManager {

    private static int totalVehicles = 0;
    static final String COMPANY = "FastFleet Co.";

    static void registerVehicle() { totalVehicles++; }

    static int getTotalVehicles() { return totalVehicles; }

}
