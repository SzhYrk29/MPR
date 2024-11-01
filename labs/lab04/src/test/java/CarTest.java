import org.example.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    @Test
    public void carTurnsOnWhenItHasBatteryAndFuel() {
        Car car = new Car(30, 20, 50, 0.8, 0);

        boolean result = car.turnOn();

        assertTrue(result);
    }

    @Test
    public void carDoesntTurnOnWhenItHasBatteryAndNoFuel() {
        Car car = new Car(30, 5, 50, 0.8, 0);

        boolean result = car.turnOn();

        assertFalse(result);
    }

    @Test
    public void carDoesntTurnOnWhenItHasFuelAndNoBattery() {
        Car car = new Car(10, 20, 50, 0.8, 0);

        boolean result = car.turnOn();

        assertFalse(result);
    }

    @Test
    public void carDoesntTurnOnWhenItHasNoBatteryAndNoFuel() {
        Car car = new Car(10, 5, 50, 0.8, 0);

        boolean result = car.turnOn();

        assertFalse(result);
    }

    @Test
    public void carRefuelIsSuccessful() {
        Car car = new Car(10, 20, 50, 0.8, 0);

        boolean result = car.refuel(20);

        assertTrue(result);
    }

    @Test
    public void carRefuelWithNegativeValue() {
        Car car = new Car(10, 20, 50, 0.8, 0);

        boolean result = car.refuel(-40);

        assertFalse(result);
    }

    @Test
    public void carRefuelWithTooMuchFuel() {
        Car car = new Car(10, 20, 50, 0.8, 0);

        boolean result = car.refuel(60);

        assertTrue(result);
    }

    @Test
    public void carDrivesSuccessfully() {
        Car car = new Car(10, 100, 100, 0.8, 0);

        boolean result = car.drive(60);

        assertTrue(result);
    }

    @Test
    public void carDoesntDriveSuccessfullyAfterThreeTries() {
        Car car = new Car(10, 100, 100, 0.8, 0);

        boolean result = car.drive(10);
        boolean result1 = car.drive(10);
        boolean result2 = car.drive(10000);

        assertTrue(result);
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void carDrivesSuccessfullyAfterThreeTries() {
        Car car = new Car(10, 100, 100, 0.8, 0);

        boolean result = car.drive(10);
        boolean result1 = car.drive(10);
        boolean result2 = car.drive(10);

        assertTrue(result);
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void carDoesntDrive() {
        Car car = new Car(10, 100, 100, 0.8, 0);

        boolean result = car.drive(500);

        assertFalse(result);
    }

    @Test
    public void reduceFuelIsSuccessful() {
        Car car = new Car(10, 100, 100, 0.8, 0);

        boolean result = car.zmniejszamyPaliwo(20);

        assertTrue(result);
    }

    @Test
    public void reduceFuelWithAnAmountGreaterThanFuelAmountInACar() {
        Car car = new Car(10, 100, 100, 0.8, 0);

        boolean result = car.zmniejszamyPaliwo(110);

        assertFalse(result);
    }

    @Test
    public void reduceFuelWithNegativeValue() {
        Car car = new Car(10, 100, 100, 0.8, 0);

        boolean result = car.zmniejszamyPaliwo(-20);

        assertFalse(result);
    }
}