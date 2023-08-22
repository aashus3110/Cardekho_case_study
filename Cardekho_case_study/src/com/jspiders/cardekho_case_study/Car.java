package com.jspiders.cardekho_case_study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jspiders.cardekho_case_study.entity.Entity;
import com.jspiders.cardekho_case_study.operation.Operation;

public class Car {
    @SuppressWarnings("unused")
	private static List<Entity> carList = new ArrayList<>();

    public static void main(String[] args) {
        boolean loop = true;
        while (loop) {
            loop = carDekho();
        }
    }

    private static boolean carDekho() {
        System.out.println("==============================================");
        System.out.println("Welcome To Car Dekho ");
        System.out.println("==============================================");
        System.out.println("1: Add, Remove, Modify Car Details");
        System.out.println("2: Display all Car Details");
        System.out.println("3: Search the Car");
        System.out.println("4: Exit");
        System.out.println("\n==============================================");

        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter your Choice");
        int choice1 = sc.nextInt();
        switch (choice1) {
            case 1:
                System.out.println("\n==============================================");
                System.out.println("Welcome To Add, Remove, Modify Car Details ");
                System.out.println("1: Add");
                System.out.println("2: Remove");
                System.out.println("3: Modify");
                System.out.println("4: Go Back");
                System.out.println("\nEnter your Choice");
                int choice2 = sc.nextInt();
                switch (choice2) {
                    case 1:
                        System.out.println("\n==============================================");
                        System.out.println("Add Car Details");
                        Operation.addCarDetails(); // Call the method from Operation class
                        System.out.println("\nCar Details Added Successfully!");
                        System.out.println("\n==============================================");
                        break;
                    case 2:
                        System.out.println("\n==============================================");
                        System.out.println("\n\tRemove Car Details");
                        System.out.println("Enter the car ID to remove:");
                        int carIdToRemove = sc.nextInt();
                        Operation.removeCarDetails(carIdToRemove); // Remove car details
                        System.out.println("\n==============================================");
                        break;
                    case 3:
                        System.out.println("\n==============================================");
                        System.out.println("\n\tModify Car Details");
                        System.out.println("Enter the car ID to modify:");
                        int carIdToModify = sc.nextInt();
                        Operation.modifyCarDetails(carIdToModify);
                        System.out.println("\n==============================================");
                        break;
                    case 4:
                        return true;
                    default:
                        System.out.println("************************************************");
                        System.out.println("\n\tInvalid choice! Please try again.");
                        System.out.println("************************************************");
                        break;
                }
                System.out.println("\n==============================================");
                break;
            case 2:
                System.out.println("\n==============================================");
                Operation.displayCarDetails();
                System.out.println("\n==============================================");
                break;
            case 3:
                System.out.println("\n==============================================");
                System.out.println("Search the Car");
                Operation.searchCarByDetail();
                System.out.println("\n==============================================");
                break;

            case 4:
                System.out.println("\n==============================================");
                System.out.println("Exit....");
                return false;
            default:
                System.out.println("************************************************");
                System.out.println("\n\tInvalid choice! Please try again.");
                System.out.println("\n************************************************");
                break;
        }
        return true;
    }
}
