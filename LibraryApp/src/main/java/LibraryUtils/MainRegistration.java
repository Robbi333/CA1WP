package LibraryUtils;

import daos.Dao;
import Business.members;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate; // Import LocalDate
import java.util.Scanner;

public class MainRegistration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Library Member Registration");

        // Collect member information
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Address Line 1: ");
        String AddLine1 = scanner.nextLine();

        System.out.print("Enter Address Line 2: ");
        String AddLine2 = scanner.nextLine();

        System.out.print("Enter Eircode: ");
        String Eircode = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String Phone_Num = scanner.nextLine();

        // Set registration date to the current local date
        LocalDate registrationDate = LocalDate.now();

        // Create a Member object
        members member = new members(username, password, firstName, lastName, email, AddLine1, AddLine2, Eircode, Phone_Num, registrationDate);


        // Establish a database connection
        try (Connection connection = daos.Dao.getConnection()) {
            if (connection != null) {
                // Insert the member into the database
                boolean registrationSuccessful = daos.MembersDao.insertMember(member);

                if (registrationSuccessful) {
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("Registration failed. Please try again.");
                }
            } else {
                System.out.println("Connection to the database failed.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
