package com.serwis.serwissamochodowy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final List<Car> activeCars;
    private final String dataFilePath;

    public CarService(@Value("${serwissamochodowy.datafile.path}") String dataFilePath) {
        this.activeCars = new ArrayList<>();
        this.dataFilePath = dataFilePath;
        loadCarsFromFile();
    }

    public List<Car> getActiveCars() {
        return activeCars;
    }

    public void addCar(Car car) {
        activeCars.add(car);
        saveCarsToFile();
    }



    public void repairCar(String registrationNumber) {
        for (Car car : activeCars) {
            if (car.getRegistrationNumber().equals(registrationNumber)) {
                car.setFixed(true);
                saveCarsToFile();
                break;
            }
        }
    }

    public Car getCarByRegistrationNumber(String registrationNumber) {
        for (Car car : activeCars) {
            if (car.getRegistrationNumber().equals(registrationNumber)) {
                return car;
            }
        }
        return null;
    }
    public List<Car> getCarsToRepair() {
        List<Car> carsToRepair = new ArrayList<>();
        for (Car car : activeCars) {
            if (!car.isFixed()) {
                carsToRepair.add(car);
            }
        }
        return carsToRepair;
    }


    public List<Car> getRepairedCars() {
        return activeCars.stream()
                .filter(Car::isFixed)
                .collect(Collectors.toList());
    }

    private void loadCarsFromFile() {
        try {
            List<Car> cars = FileIOUtils.loadCarsFromFile(dataFilePath);
            activeCars.addAll(cars);
        } catch (IOException e) {
            System.err.println("Failed to load cars from file: " + e.getMessage());
        }
    }

    private void saveCarsToFile() {
        try {
            FileIOUtils.saveCarsToFile(activeCars, dataFilePath);
        } catch (IOException e) {
            System.err.println("Failed to save cars to file: " + e.getMessage());
        }
    }
}