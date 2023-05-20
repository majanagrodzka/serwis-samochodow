package com.serwis.serwissamochodowy;

import java.time.LocalDate;

public class Car {
    private String registrationNumber;
    private String name;
    private String color;
    private int year;
    private LocalDate entryDate;
    private boolean isFixed;

    public Car() {
    }

    public Car(String registrationNumber, String name, String color, int year, LocalDate entryDate, boolean isFixed) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.color = color;
        this.year = year;
        this.entryDate = entryDate;
        this.isFixed = isFixed;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }
}
