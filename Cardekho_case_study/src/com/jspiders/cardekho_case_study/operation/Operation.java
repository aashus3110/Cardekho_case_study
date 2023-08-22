package com.jspiders.cardekho_case_study.operation;

import com.jspiders.cardekho_case_study.entity.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Operation {
    private static List<Entity> carList = new ArrayList<>();

    public static void addCarDetails() {
        Scanner sc = new Scanner(System.in);
        Entity car;
        
        System.out.println("Enter the number of car details to add:");
        int count = sc.nextInt();

        for (int i = 0; i < count; i++) {
            car = new Entity();

            System.out.println("\nEnter Car ID:");
            int id = sc.nextInt();
            car.setCar_Id(id);

            System.out.println("Enter Car Name:");
            String name = sc.next();
            car.setCar_Name(name);

            System.out.println("Enter Car Brand:");
            String brand = sc.next();
            car.setCar_Brand(brand);

            System.out.println("Enter Fuel Type:");
            String fuelType = sc.next();
            car.setFule_Type(fuelType);

            System.out.println("Enter Car Price:");
            int price = sc.nextInt();
            car.setPrice(price);

            carList.add(car);
            System.out.println("Car Details Added Successfully!");
            
            // Display the car details after adding
            displayCarDetails();
            
        }
    }
   // Display car details
   
    public static void displayCarDetails() {
        if (carList.isEmpty()) {
            System.out.println("No car details available.");
        } else {
            System.out.println("Car Details:");
            System.out.println("Car ID \t Car Name \t Car Brand \t Fuel Type \t Car Price");
            System.out.println("------------------------------------------------------------------------------");
            for (Entity car : carList) {
                System.out.print(car.getCar_Id() + "\t");  
                System.out.print(car.getCar_Name() + "\t\t");
                System.out.print(car.getCar_Brand() + "\t\t");
                System.out.print(car.getFule_Type() + "\t\t");
                System.out.println(car.getPrice());
            }
        }
    }

    public static void removeCarDetails(int carId) {
        Iterator<Entity> iterator = carList.iterator();
        boolean carFound = false;
        while (iterator.hasNext()) {
            Entity car = iterator.next();
            if (car.getCar_Id() == carId) {
                iterator.remove();
                System.out.println("Car with ID " + carId + " removed successfully.");
                carFound = true;
                break;
            }
        }
        if (!carFound) {
            System.out.println("Car with ID " + carId + " not found. Please enter a valid car ID.");
        }
    }

    public static void modifyCarDetails(int carId) {
        Scanner sc = new Scanner(System.in);
        boolean carFound = false;

        for (Entity car : carList) {
            if (car.getCar_Id() == carId) {
                carFound = true;

                System.out.println("Modifying Car Details (ID: " + carId + "):");
                System.out.println("1. Car Name");
                System.out.println("2. Car Brand");
                System.out.println("3. Fuel Type");
                System.out.println("4. Car Price");
                System.out.println("5. Go Back");

                System.out.println("Enter your choice:");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.println("Enter new Car Name:");
                        String newName = sc.nextLine();
                        car.setCar_Name(newName);
                        System.out.println("Car Name modified successfully.");
                        break;
                    case 2:
                        System.out.println("Enter new Car Brand:");
                        String newBrand = sc.nextLine();
                        car.setCar_Brand(newBrand);
                        System.out.println("Car Brand modified successfully.");
                        break;
                    case 3:
                        System.out.println("Enter new Fuel Type:");
                        String newFuelType = sc.nextLine();
                        car.setFule_Type(newFuelType);
                        System.out.println("Fuel Type modified successfully.");
                        break;
                    case 4:
                        System.out.println("Enter new Car Price:");
                        int newPrice = sc.nextInt();
                        car.setPrice(newPrice);
                        System.out.println("Car Price modified successfully.");
                        break;
                    case 5:
                        return; // Go back to the previous menu
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }

                break;
            }
        }

        if (!carFound) {
            System.out.println("Car with ID " + carId + " not found. Please enter a valid car ID.");
        }
    }

    public static void searchCarByDetail() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Search Car by Detail");
        System.out.println("1. Search by Car Name");
        System.out.println("2. Search by Car Brand");
        System.out.println("3. Search by Fuel Type");
        System.out.println("4. Go Back");

        System.out.println("Enter your choice:");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                System.out.println("Enter Car Name to search:");
                String searchName = sc.nextLine();
                searchCarByCarName(searchName);
                break;
            case 2:
                System.out.println("Enter Car Brand to search:");
                String searchBrand = sc.nextLine();
                searchCarByCarBrand(searchBrand);
                break;
            case 3:
                System.out.println("Enter Fuel Type to search:");
                String searchFuelType = sc.nextLine();
                searchCarByFuelType(searchFuelType);
                break;
            case 4:
                return; // Go back to the menu
            default:
                System.out.println("Invalid choice! Please try again.");
                break;
        }
    }

    public static void searchCarByCarName(String searchName) {
        boolean carFound = false;
        for (Entity car : carList) {
            if (car.getCar_Name().equalsIgnoreCase(searchName)) {
                displayCarDetails(car);
                carFound = true;
            }
        }
        if (!carFound) {
            System.out.println("No car with the given name found.");
        }
    }

    public static void searchCarByCarBrand(String searchBrand) {
        boolean carFound = false;
        for (Entity car : carList) {
            if (car.getCar_Brand().equalsIgnoreCase(searchBrand)) {
                displayCarDetails(car);
                carFound = true;
            }
        }
        if (!carFound) {
            System.out.println("No car with the given brand found.");
        }
    }

    public static void searchCarByFuelType(String searchFuelType) {
        boolean carFound = false;
        for (Entity car : carList) {
            if (car.getFule_Type().equalsIgnoreCase(searchFuelType)) {
                displayCarDetails(car);
                carFound = true;
            }
        }
        if (!carFound) {
            System.out.println("No car with the given fuel type found.");
        }
    }

    public static void displayCarDetails(Entity car) {
        System.out.println("Car Details:");
        System.out.println("Car ID \t Car Name \t Car Brand \t Fuel Type \t Car Price");
        System.out.println("------------------------------------------------------------------------------");
        System.out.print(car.getCar_Id() + "\t");
        System.out.print(car.getCar_Name() + "\t\t");
        System.out.print(car.getCar_Brand() + "\t\t");
        System.out.print(car.getFule_Type() + "\t\t");
        System.out.println(car.getPrice());
    }

}
