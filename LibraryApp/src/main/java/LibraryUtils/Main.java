package LibraryUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Swag");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String userType;
        boolean isAuthenticated = false;
        do {
            System.out.print("Enter your user type (member/admin): ");
            userType = scanner.nextLine().toLowerCase();
        } while (!userType.equals("member") && !userType.equals("admin"));

        while (true) {
            if (isAuthenticated) {
                // Display options for authenticated users
                System.out.println("Select an option:");
                System.out.println("3. View all books");
                System.out.println("4. View active loans");
                System.out.println("5. View past loans");
                System.out.println("6. Borrow a book");
                System.out.println("7. Return a book");
                System.out.println("8. View late fees");
                System.out.println("9. Pay late fee");
                System.out.println("10. Logout");

                if (userType.equals("admin")) {
                    System.out.println("11. Add a book");
                    System.out.println("12. Update book stock");
                    System.out.println("13. Disable member");
                }
            } else {
                // Display options for non-authenticated users
                System.out.println("Select an option:");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("0. Exit");
            }


            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                  //register
                    //call the register and then set isAuthenticated to true
                    isAuthenticated = true;
                    break;
                case 2:
                   //login
                   // call the login and then set isAuthenticated to true
                    isAuthenticated = true;
                    break;
                case 3:
                    //view all books
                    break;
                case 4:
                    //view active loans
                    break;
                case 5:
                    //view past loans since joining
                    break;
                case 6:
                    //loan book
                    break;
                case 7:
                    // return book
                    break;
                case 8:
                    //view late views
                    break;
                case 9:
                    //pay late fees
                    break;
                case 10:
                    //logout
                    break;
                case 11:
                    if (userType.equals("admin")) {
                        // add a book
                    }else{
                        //update book count
                    }//
                       //disable member
                  //  }
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

}
