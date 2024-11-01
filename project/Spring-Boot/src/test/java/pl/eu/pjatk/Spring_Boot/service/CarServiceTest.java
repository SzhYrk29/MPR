package pl.eu.pjatk.Spring_Boot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import pl.eu.pjatk.Spring_Boot.model.Car;
import pl.eu.pjatk.Spring_Boot.repository.CarRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CarServiceTest {

    private CarRepository carRepository;
    private StringUtilsService stringUtilsService;
    private CarService underTest;

    @BeforeEach
    void setUp() {
        this.carRepository = Mockito.mock(CarRepository.class);
        this.stringUtilsService = Mockito.mock(StringUtilsService.class);
        this.underTest = new CarService(carRepository, stringUtilsService);

        Mockito.when(stringUtilsService.toUpperCase(Mockito.anyString()))
                .thenAnswer(invocation -> ((String) invocation.getArgument(0)).toUpperCase());
    }

    @Test
    void getAllCars() {
        underTest.getCars();
        verify(carRepository).findAll();
    }

    @Test
    public void addCar() {
        Car car = new Car("Toyota", "Blue");
        underTest.addCar(car);
        verify(carRepository).save(car);
    }

    @Test
    void addCarButInAFancyWay() {
        Car car = new Car("Toyota", "Blue");

        Mockito.when(stringUtilsService.toUpperCase("Toyota")).thenReturn("TOYOTA");
        Mockito.when(stringUtilsService.toUpperCase("Blue")).thenReturn("BLUE");

        underTest.addCar(car);

        ArgumentCaptor<Car> carArgumentCaptor = ArgumentCaptor.forClass(Car.class);
        verify(carRepository, times(1)).save(carArgumentCaptor.capture());

        Car capturedCar = carArgumentCaptor.getValue();
        assertThat(capturedCar.getBrand()).isEqualTo("TOYOTA");
        assertThat(capturedCar.getColor()).isEqualTo("BLUE");
    }


    @Test
    void getCarByBrand() {
        Car car1 = new Car("TOYOTA", "BLUE");
        Car car2 = new Car("TOYOTA", "WHITE");
        String brand = "TOYOTA";
        List<Car> cars = Arrays.asList(car1, car2);

        Mockito.when(carRepository.findByBrand(brand)).thenReturn(cars);
        Mockito.when(stringUtilsService.toLowerCaseExceptFirstLetter("TOYOTA")).thenReturn("Toyota");
        Mockito.when(stringUtilsService.toLowerCaseExceptFirstLetter("BLUE")).thenReturn("Blue");

        List<Car> result = underTest.getCarByBrand(brand);

        assertThat(result.get(0).getBrand()).isEqualTo("Toyota");
        assertThat(result.get(1).getBrand()).isEqualTo("Toyota");
    }

    @Test
    void getCarByColor() {
        Car car1 = new Car("TOYOTA", "BLUE");
        Car car2 = new Car("TOYOTA", "WHITE");
        String color1 = "BLUE";
        String color2 = "WHITE";
        List<Car> cars = Arrays.asList(car1, car2);

        Mockito.when(carRepository.findByColor(color1)).thenReturn(cars);
        Mockito.when(carRepository.findByColor(color2)).thenReturn(cars);
        Mockito.when(stringUtilsService.toLowerCaseExceptFirstLetter("TOYOTA")).thenReturn("Toyota");
        Mockito.when(stringUtilsService.toLowerCaseExceptFirstLetter("BLUE")).thenReturn("Blue");
        Mockito.when(stringUtilsService.toLowerCaseExceptFirstLetter("WHITE")).thenReturn("White");

        List<Car> result1 = underTest.getCarByColor(color1);
        assertThat(result1.get(0).getColor()).isEqualTo("Blue");
        List<Car> result2 = underTest.getCarByColor(color2);
        assertThat(result2.get(1).getColor()).isEqualTo("White");
    }

    @Test
    void returnsCarWhenFound() {
        Car car = new Car("TOYOTA", "BLUE");
        Long carId = 1L;
        Mockito.when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        Mockito.when(stringUtilsService.toLowerCaseExceptFirstLetter("TOYOTA")).thenReturn("Toyota");
        Mockito.when(stringUtilsService.toLowerCaseExceptFirstLetter("BLUE")).thenReturn("Blue");

        Optional<Car> result = underTest.getCar(carId);

        assertThat(result).isPresent();
        assertThat(result.get().getBrand()).isEqualTo("Toyota");
        assertThat(result.get().getColor()).isEqualTo("Blue");
        verify(carRepository).findById(carId);
    }

    @Test
    public void returnsEmptyWhenCarNotFound() {
        Long carId = 1L;
        Mockito.when(carRepository.findById(carId)).thenReturn(Optional.empty());

        Optional<Car> result = underTest.getCar(carId);

        assertThat(result).isNotPresent();
    }

    @Test
    void deleteCar() {
        Long carId = 1L;
        Mockito.when(carRepository.existsById(carId)).thenReturn(true);
        underTest.deleteCar(carId);
        verify(carRepository).deleteById(carId);
    }

    @Test
    void updateCar() {
        Long carId = 1L;
        Car car = new Car("Toyota", "Blue");
        Car updatedCar = new Car("TOYOTA", "BLUE");

        Mockito.when(carRepository.existsById(carId)).thenReturn(true);
        Mockito.when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        Mockito.when(stringUtilsService.toUpperCase("Toyota")).thenReturn("TOYOTA");
        Mockito.when(stringUtilsService.toUpperCase("Blue")).thenReturn("BLUE");

        underTest.updateCar(carId, updatedCar);

        verify(carRepository).save(car);
        assertThat(car.getBrand()).isEqualTo("TOYOTA");
        assertThat(car.getColor()).isEqualTo("BLUE");
    }
}