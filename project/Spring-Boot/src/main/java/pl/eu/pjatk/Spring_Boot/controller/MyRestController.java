package pl.eu.pjatk.Spring_Boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.eu.pjatk.Spring_Boot.model.Car;
import pl.eu.pjatk.Spring_Boot.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    private CarService carService;

    @Autowired
    public MyRestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("car/all") // <- endpoint (displays all cars)
    public Iterable<Car> getAll() {
        return this.carService.getCars();
    }

    @GetMapping("car/{id}") // <- endpoint (get a car by id)
    public Optional<Car> get(@PathVariable Long id) {
        return this.carService.getCar(id);
    }

    @PostMapping("car") // <- endpoint (adds a new car)
    public void addCar(@RequestBody Car car) {
        this.carService.addCar(car);
    }

    @DeleteMapping("car/delete/{id}") // <- endpoint (deletes a car)
    public void deleteCar(@PathVariable Long id) {
        this.carService.deleteCar(id);
    }

    @PutMapping("car/update/{id}") // <- endpoint (updates car's info)
    public void updateCar(@PathVariable Long id, @RequestBody Car car) {
        this.carService.updateCar(id, car);
    }

    @GetMapping("car/brand/{brand}") // <- endpoint (get a car by brand)
    public List<Car> getByBrand(@PathVariable String brand) {
        return this.carService.getCarByBrand(brand);
    }

    @GetMapping("car/color/{color}") // <- endpoint (get a car by color)
    public List<Car> getByColor(@PathVariable String color) {
        return this.carService.getCarByColor(color);
    }
}