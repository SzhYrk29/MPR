package pl.eu.pjatk.Spring_Boot;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import pl.eu.pjatk.Spring_Boot.repository.CarRepository;
import pl.eu.pjatk.Spring_Boot.service.CarService;
import pl.eu.pjatk.Spring_Boot.service.StringUtilsService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarServiceTest {

    private CarRepository carRepository;
    private StringUtilsService stringUtilsService;
    private CarService carServiceTest;

    @BeforeEach
    public void setUp() {
        this.carRepository = Mockito.mock(CarRepository.class);
        this.stringUtilsService = Mockito.mock(StringUtilsService.class);
        this.carServiceTest = new CarService(carRepository, stringUtilsService);
    }

//    @Test
//    public void addCar() {
//        Car car = new Car("BMW", "white");
//
//        Mockito.when(stringUtilsService.toUpperCase(car.getBrand())).thenReturn("BMW");
//        Mockito.when(stringUtilsService.toUpperCase(car.getColor())).thenReturn("white");
//
//        carServiceTest.addCar(car);
//
//        Mockito.verify(stringUtilsService).toUpperCase(car.getBrand());
//        Mockito.verify(stringUtilsService).toUpperCase(car.getColor());
//        Mockito.verify(carRepository.save(car));
//
//        assertEquals("BMW", car.getBrand());
//        assertEquals("white", car.getColor());
//    }
}