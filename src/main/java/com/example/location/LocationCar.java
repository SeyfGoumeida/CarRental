package com.example.location;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class LocationCar {
    private List<Car> cars = new ArrayList<Car>();
    public LocationCar(){
        cars.add(new Car("11AA22", "Ferrari", 1000));
        cars.add(new Car("33BB44", "Porshe", 2222));
    }

    @GetMapping(value ="/testcar")
    public Car getCar(){
        Car myCar = new Car("123456789","TestCar",000000);
        return myCar;
    }

    @GetMapping("/cars")
    public List<Car> getListOfCars(){
        return cars;
    }

    @PostMapping("/cars/addcar")
    public void addCar(@RequestBody Car car) throws Exception{
        System.out.println(car);
        cars.add(car);
    }
    //Delete using GETMAPPING
    @GetMapping("/cars/deletecar/{plateNumber}")
    public String deleteCar(@PathVariable(value = "plateNumber") String plateNumber){
        for(Car car: cars){
            if(car.getPlateNumber().equals(plateNumber)){
                cars.remove(car);
                return car.toString()+"  Has Been Deleted Successfully ";
            }
        }
        return null;
    }
    //Delete using DeleteMapping
    @DeleteMapping("/cars/deletecar")
    public void deleteCar(@RequestBody Car car) throws Exception{
        for(Car cartemp: cars){
            if(car.getPlateNumber().equals(cartemp.getPlateNumber())){
                System.out.println(car + "  Has Been Deleted Successfully ");
                cars.remove(cartemp);
            }
        }
    }
    //Search using GETMAPPING
    @GetMapping("/cars/search/{plateNumber}")
    public Car getCar(@PathVariable(value = "plateNumber") String plateNumber){
        for(Car car: cars){
            if(car.getPlateNumber().equals(plateNumber)){
                return car;
            }
        }
        return null;
    }

    @PutMapping("/cars/rent/{plateNumber}")
    public void rent(@PathVariable(value = "plateNumber") String plateNumber,
                     @RequestParam(value= "rent" ,required = true) boolean r ,
                     @RequestBody Dates dates){
                System.out.println(plateNumber);
                System.out.println(dates);
                    for(Car car: cars) {
                        if (car.getPlateNumber().equals(plateNumber)) {
                            if(r == true){
                                car.getDates().add(dates);
                                car.setRent(true);
                            }else{
                                car.setRent(false);
                            }
                    }

                }
    }
    @PutMapping("/cars/rentback/{plateNumber}")
    public void rentBack(@PathVariable(value = "plateNumber") String plateNumber,
                     @RequestParam(value= "rent" ,required = true) boolean r) {
        System.out.println(plateNumber);
        for(Car car: cars) {
            if (car.getPlateNumber().equals(plateNumber)) {
                    car.setRent(false);
            }
        }
    }


}