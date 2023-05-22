package com.serwis.serwissamochodowy;

import com.serwis.serwissamochodowy.filehandler.FileIOUtils;
import com.serwis.serwissamochodowy.model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final List<Car> activeCars;
    private final String dataFilePath;

    public CarService(@Value("${ss.datafile.path}") String dataFilePath) {
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

    public List<Car> getCarsToRepair() {
        List<Car> carsToRepair = new ArrayList<>();
        for (Car car : activeCars) {
            if (!car.isFixed()) {
                carsToRepair.add(car);
            }
        }
        Collections.sort(carsToRepair, Comparator.comparing(Car::getEntryDate));
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
