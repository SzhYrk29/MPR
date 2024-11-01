package pl.eu.pjatk.Spring_Boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.eu.pjatk.Spring_Boot.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository <Car, Long> {
    public List<Car> findByBrand(String brand);
    public List<Car> findByColor(String color);
}