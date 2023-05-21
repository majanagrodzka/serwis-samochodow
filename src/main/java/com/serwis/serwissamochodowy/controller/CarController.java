package com.serwis.serwissamochodowy.controller;

import com.serwis.serwissamochodowy.CarService;
import com.serwis.serwissamochodowy.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String showActiveCars(Model model) {
        List<Car> carsToRepair = carService.getCarsToRepair();
        model.addAttribute("cars", carsToRepair);
        return "index";
    }
    @GetMapping("/add")
    public String showAddCarForm(Model model) {
        model.addAttribute("car", new Car());

        List<Integer> yearsList = new ArrayList<>();
        int currentYear = Year.now().getValue();
        for (int year = currentYear; year >= 1900; year--) {
            yearsList.add(year);
        }
        model.addAttribute("yearsList", yearsList);

        return "add-car";
    }

    @PostMapping("/add")
    public String addCar(Car car) {
        car.setEntryDate(LocalDate.now());
        carService.addCar(car);
        return "redirect:/";
    }


    @GetMapping("/repaired")
    public String showRepairedCars(Model model) {
        model.addAttribute("cars", carService.getRepairedCars());
        return "repaired-cars";
    }
    @GetMapping("/repair")
    public String showCarsToRepair(Model model) {
        List<Car> carsToRepair = carService.getCarsToRepair();
        model.addAttribute("cars", carsToRepair);
        return "repair-car";
    }


    @PostMapping("/repair/{registrationNumber}")
    public String repairCar(@PathVariable String registrationNumber) {
        carService.repairCar(registrationNumber);
        return "redirect:/";
    }
}

