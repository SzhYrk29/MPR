package org.example;

public class Car {
    private int battery;
    private double fuelAmount;
    private double fuelCapacity;
    private double fuelConsumption;
    private int distanceTravelled;

    public Car(int battery, double fuelAmount, double fuelCapacity, double fuelConsumption, int distanceTravelled) {
        this.battery = battery;
        this.fuelAmount = fuelAmount;
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumption = fuelConsumption;
        this.distanceTravelled = distanceTravelled;
    }

    public Car() {

    }

    public int getBattery() {
        return battery;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setFuelAmount(int fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void setDistanceTravelled(int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public boolean turnOn() {
        if (this.fuelAmount > 10 && this.battery > 15) {
            return true;
        } else {
            return false;
        }
    }

    public boolean drive(int distance) {
        if (distance > 0) {
            double fuelNeeded = this.fuelConsumption * distance;
            if (this.fuelAmount >= fuelNeeded) {
                this.distanceTravelled += distance;
                this.fuelAmount -= fuelNeeded;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public boolean zmniejszamyPaliwo(int amount) {
        if (amount < 0) {
            return false;
        }
        if (amount <= this.fuelAmount) {
            this.fuelAmount -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean refuel(int fuel) {
        if (fuel > 0) {
            this.fuelAmount += fuel;
            if (this.fuelAmount > fuelCapacity) {
                this.fuelAmount = fuelCapacity;
            }
            return true;
        } else {
            return false;
        }
    }
}