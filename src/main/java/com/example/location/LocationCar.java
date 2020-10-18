package com.example.location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationCar {
    private List<Car> cars = new ArrayList<Car>();
    public LocationCar(){
        cars.add(new Car("11AA22", "Ferrari", 1000));
        cars.add(new Car("33BB44", "Porshe", 2222));
    }
    @GetMapping(value ="/car")
    public Car getCar(){
        Car myCar = new Car("0778052274","Reneault",10000);
        return myCar;
    }
    @GetMapping("/cars")
    public List<Car> getListOfCars(){
        return cars;
    }
    @GetMapping("/cars/{plateNumber}")
    public Car getCar(@PathVariable(value = "plateNumber") String plateNumber){
        for(Car car: cars){
            if(car.getPlateNumber().equals(plateNumber)){
                return car;
            }
        }
        return null;
    }
}