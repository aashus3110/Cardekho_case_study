package com.jspiders.car_dekho_jdbc.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operation {

    public static boolean carDekho(Connection connection) {
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
                        addCarDetails(connection);
                        System.out.println("\nCar Details Added Successfully!");
                        System.out.println("\n==============================================");
                        break;
                    case 2:
                        System.out.println("\n==============================================");
                        System.out.println("\n\tRemove Car Details");
                        System.out.println("Enter the car ID to remove:");
                        removeCarDetails(connection);
                        System.out.println("\n==============================================");
                        break;
                    case 3:
                        System.out.println("\n==============================================");
                        System.out.println("\n\tModify Car Details");
                        modifyCarDetails(connection);
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
                displayAllCarDetails(connection);
                System.out.println("\n==============================================");
                break;
            case 3:
                System.out.println("\n==============================================");
                System.out.println("Search the Car");
                searchCarByEntity(connection);

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

    private static List<Integer> newCarIds = new ArrayList<>();

    public static void addCarDetails(Connection connection) {
        String insertQuery = "INSERT INTO cardetail (carId, carName, carBrand, fuleType, price) VALUES (?, ?, ?, ?, ?)";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of car details to add:");
        int count = scanner.nextInt();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            for (int i = 0; i < count; i++) {
                System.out.println("\nEnter Car ID:");
                int id = scanner.nextInt();
                preparedStatement.setInt(1, id);

                System.out.println("Enter Car Name:");
                String name = scanner.next();
                preparedStatement.setString(2, name);

                System.out.println("Enter Car Brand:");
                String brand = scanner.next();
                preparedStatement.setString(3, brand);

                System.out.println("Enter Fuel Type:");
                String fuelType = scanner.next();
                preparedStatement.setString(4, fuelType);

                System.out.println("Enter Car Price:");
                int price = scanner.nextInt();
                preparedStatement.setInt(5, price);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    newCarIds.add(id);
                    displayNewCarDetails(connection);
                    System.out.println(rowsAffected + " Car Details Added Successfully!");
                } else {
                    System.out.println("Failed to add car details.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAllCarDetails(Connection connection) {
        String selectQuery = "SELECT * FROM cardetail";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("==============================================");
            System.out.println("All Car Details:");
            System.out.println("==============================================");
            System.out.println("Car ID\tCar Name\tCar Brand\tFuel Type\tPrice");
            System.out.println("==============================================");

            while (resultSet.next()) {
                int carId = resultSet.getInt("carId");
                String carName = resultSet.getString("carName");
                String carBrand = resultSet.getString("carBrand");
                String fuelType = resultSet.getString("fuleType");
                int price = resultSet.getInt("price");

                System.out.println(carId + "\t" + carName + "\t\t" + carBrand + "\t\t" + fuelType + "\t\t" + price);
            }

            System.out.println("==============================================");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    private static void displayNewCarDetails(Connection connection) {
        if (newCarIds.isEmpty()) {
            System.out.println("No new car details added.");
            return;
        }

        String selectQuery = "SELECT * FROM cardetail WHERE carId IN (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            String carIdsString = newCarIds.toString().replaceAll("[\\[\\]]", "");

            preparedStatement.setString(1, carIdsString);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("==============================================");
            System.out.println("New Car Details:");
            System.out.println("==============================================");
            System.out.println("Car ID\tCar Name\tCar Brand\tFuel Type\tPrice");
            System.out.println("==============================================");

            while (resultSet.next()) {
                int carId = resultSet.getInt("carId");
                String carName = resultSet.getString("carName");
                String carBrand = resultSet.getString("carBrand");
                String fuelType = resultSet.getString("fuleType");
                int price = resultSet.getInt("price");

                System.out.println(carId + "\t" + carName + "\t\t" + carBrand + "\t\t" + fuelType + "\t\t" + price);
            }

            System.out.println("==============================================");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            newCarIds.clear();
        }
    }

    public static void displayCarIds(Connection connection) {
        String selectIdsQuery = "SELECT carId FROM cardetail";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectIdsQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("==============================================");
            System.out.println("All Car IDs:");
            System.out.println("==============================================");

            while (resultSet.next()) {
                int carId = resultSet.getInt("carId");
                System.out.println(carId);
            }

            System.out.println("==============================================");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeCarDetails(Connection connection) {
        displayCarIds(connection);

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many car details do you want to remove?");
        int countToRemove = scanner.nextInt();

        for (int i = 0; i < countToRemove; i++) {
            System.out.println("Enter car ID to remove:");
            int carIdToRemove = scanner.nextInt();

            String deleteQuery = "DELETE FROM cardetail WHERE carId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, carIdToRemove);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Car Details with ID " + carIdToRemove + " removed successfully!");
                } else {
                    System.out.println("Failed to remove car details with ID " + carIdToRemove + ".");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void modifyCarDetails(Connection connection) {
        displayCarIds(connection);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car ID to modify:");
        int carIdToModify = scanner.nextInt();

        if (!carExists(connection, carIdToModify)) {
            System.out.println("Car with ID " + carIdToModify + " does not exist.");
            return;
        }

        System.out.println("Select the field to modify:");
        System.out.println("1: Car Name");
        System.out.println("2: Car Brand");
        System.out.println("3: Fuel Type");
        System.out.println("4: Car Price");
        System.out.println("5: Go Back");
        System.out.println("\nEnter your choice:");
        int modifyChoice = scanner.nextInt();

        String updateQuery;
        String columnToModify;

        switch (modifyChoice) {
            case 1:
                updateQuery = "UPDATE cardetail SET carName = ? WHERE carId = ?";
                columnToModify = "Car Name";
                break;
            case 2:
                updateQuery = "UPDATE cardetail SET carBrand = ? WHERE carId = ?";
                columnToModify = "Car Brand";
                break;
            case 3:
                updateQuery = "UPDATE cardetail SET fuleType = ? WHERE carId = ?";
                columnToModify = "Fuel Type";
                break;
            case 4:
                updateQuery = "UPDATE cardetail SET price = ? WHERE carId = ?";
                columnToModify = "Car Price";
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice! Please try again.");
                return;
        }

        scanner.nextLine();

        System.out.println("Enter the new " + columnToModify + ":");
        String newValue = scanner.nextLine();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, carIdToModify);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Car details with ID " + carIdToModify + " updated successfully!");
            } else {
                System.out.println("Failed to update car details with ID " + carIdToModify + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean carExists(Connection connection, int carId) {
        String checkQuery = "SELECT carId FROM cardetail WHERE carId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {
            preparedStatement.setInt(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void searchCarByEntity(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the entity to search by:");
        System.out.println("1: Car Name");
        System.out.println("2: Car Brand");
        System.out.println("3: Fuel Type");
        System.out.println("4: Car ID");
        System.out.println("5: Car Price");
        System.out.println("6: Go Back");
        System.out.println("\nEnter your choice:");
        int searchChoice = scanner.nextInt();

        String searchQuery;
        String columnToSearch;

        switch (searchChoice) {
            case 1:
                searchQuery = "SELECT * FROM cardetail WHERE carName = ?";
                columnToSearch = "Car Name";
                break;
            case 2:
                searchQuery = "SELECT * FROM cardetail WHERE carBrand = ?";
                columnToSearch = "Car Brand";
                break;
            case 3:
                searchQuery = "SELECT * FROM cardetail WHERE fuleType = ?";
                columnToSearch = "Fuel Type";
                break;
            case 4:
                searchQuery = "SELECT * FROM cardetail WHERE carId = ?";
                columnToSearch = "Car ID";
                break;
            case 5:
                searchQuery = "SELECT * FROM cardetail WHERE price = ?";
                columnToSearch = "Car Price";
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice! Please try again.");
                return;
        }

        scanner.nextLine();

        System.out.println("Enter the " + columnToSearch + " to search for:");
        String searchValue = scanner.nextLine();

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            if (searchChoice == 4 || searchChoice == 5) {
                int intValue = Integer.parseInt(searchValue);
                preparedStatement.setInt(1, intValue);
            } else {
                preparedStatement.setString(1, searchValue);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("==============================================");
            System.out.println("Search Results:");
            System.out.println("==============================================");
            System.out.println("Car ID\tCar Name\tCar Brand\tFuel Type\tPrice");
            System.out.println("==============================================");
            while (resultSet.next()) {
                int carId = resultSet.getInt("carId");
                String carName = resultSet.getString("carName");
                String carBrand = resultSet.getString("carBrand");
                String fuelType = resultSet.getString("fuleType");
                int price = resultSet.getInt("price");
                System.out.println(carId + "\t" + carName + "\t\t" + carBrand + "\t\t" + fuelType + "\t\t" + price);
            }
            System.out.println("==============================================");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for Car ID or Car Price. Please enter a valid number.");
        }
    }
}