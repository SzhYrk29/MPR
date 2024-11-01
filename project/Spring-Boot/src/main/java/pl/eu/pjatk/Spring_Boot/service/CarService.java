package pl.eu.pjatk.Spring_Boot.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.eu.pjatk.Spring_Boot.model.Car;
import pl.eu.pjatk.Spring_Boot.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private CarRepository repository;
    private StringUtilsService stringUtilsService;

    @Autowired
    public CarService(CarRepository repository, StringUtilsService stringUtilsService) {
        this.repository = repository;
        this.stringUtilsService = stringUtilsService;
    }

    @PostConstruct
    public void init() {
        if (this.repository.count() == 0) {
            this.repository.save(new Car("Tesla", "white"));
            this.repository.save(new Car("Lamborghini", "red"));
            this.repository.save(new Car("BMW", "black"));
        }
    }

    public List<Car> getCarByBrand (String brand) {
        List<Car> cars = this.repository.findByBrand(brand);
        List<Car> sortedCars = new ArrayList<>();
        for (Car car : cars) {
            Car sortedCar = new Car();
            sortedCar.setBrand(this.stringUtilsService.toLowerCaseExceptFirstLetter(car.getBrand()));
            sortedCars.add(sortedCar);
        }
        return sortedCars;
    }

    public List<Car> getCarByColor (String color) {
        List<Car> cars = this.repository.findByColor(color);
        List<Car> sortedCars = new ArrayList<>();
        for (Car car : cars) {
            Car sortedCar = new Car();
            sortedCar.setColor(this.stringUtilsService.toLowerCaseExceptFirstLetter(car.getColor()));
            sortedCars.add(sortedCar);
        }
        return sortedCars;
    }

    public Iterable<Car> getCars() {
        Iterable<Car> all = this.repository.findAll();
        List<Car> formattedCars = new ArrayList<>();
        for (Car car : all) {
            Car formattedCar = new Car();
            formattedCar.setBrand(this.stringUtilsService.toLowerCaseExceptFirstLetter(car.getBrand()));
            formattedCar.setColor(this.stringUtilsService.toLowerCaseExceptFirstLetter(car.getColor()));
            formattedCars.add(formattedCar);
        }
        return formattedCars;
    }

    public Optional<Car> getCar(Long id) {
        Optional<Car> cars = this.repository.findById(id);
        if (cars.isPresent()) {
            Car car = cars.get();
            Car sortedCar = new Car();
            sortedCar.setBrand(this.stringUtilsService.toLowerCaseExceptFirstLetter(car.getBrand()));
            sortedCar.setColor(this.stringUtilsService.toLowerCaseExceptFirstLetter(car.getColor()));

            return Optional.of(sortedCar);
        }
        return Optional.empty();
    }

    public void addCar(Car car) {
        car.setBrand(this.stringUtilsService.toUpperCase(car.getBrand()));
        car.setColor(this.stringUtilsService.toUpperCase(car.getColor()));
        this.repository.save(car);
    }

    public void deleteCar(Long id) {
        boolean exists = this.repository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Car with id " + id + " does not exists");
        }
        this.repository.deleteById(id);
    }

    public void updateCar(Long id, Car car) {
        boolean exists = this.repository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Car with id " + id + " does not exists");
        }

        Car existingCar = this.repository.findById(id).get();
        existingCar.setBrand(car.getBrand().toUpperCase());
        existingCar.setColor(car.getColor().toUpperCase());
        existingCar.setIdentifier();
        this.repository.save(existingCar);
    }
}