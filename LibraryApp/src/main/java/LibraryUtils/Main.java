package LibraryUtils;

import Business.members;
import daos.BookDao;
import daos.LoanDao;
import daos.MembersDao;
import exceptions.DaoException;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Swag");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        members isAuthenticated = null;
        boolean Auth = false;

        while (isAuthenticated == null) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            // Fetch user details from the database using the username
            isAuthenticated = MembersDao.authenticateMember(connection, username, password);

            if (isAuthenticated != null) {
                if (isAuthenticated.isAdmin()) {
                    Auth = true;
                    System.out.println("Admin authenticated.");
                } else {
                    Auth = true;
                    System.out.println("Member authenticated.");
                }
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        }

        while (true) {
            if (Auth = true) {
                // Display options for authenticated users
                System.out.println("Select an option:");
                System.out.println("3. View all books");
                System.out.println("4. View active loans");
                System.out.println("5. View past loans");
                System.out.println("6. Borrow a book");
                System.out.println("7. Return a book");
                System.out.println("8. View late fees");                System.out.println("9. Pay late fee");
                System.out.println("10. Logout");

                if (isAuthenticated.isAdmin()) {
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
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (!Auth) {
                        System.out.println("Registration:");
                        System.out.print("Enter your username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String password = scanner.nextLine();
                        System.out.print("Enter your first name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter your last name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter your email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter your address line 1: ");
                        String address1 = scanner.nextLine();
                        System.out.print("Enter your address line 2: ");
                        String address2 = scanner.nextLine();
                        System.out.print("Enter your Eircode: ");
                        String eircode = scanner.nextLine();
                        System.out.print("Enter your phone number: ");
                        String phoneNumber = scanner.nextLine();

                        // You can also validate user inputs here and perform registration logic
                        // Call your insertMember method here
                        members newMember = new members(username, password, firstName, lastName, email, address1, address2, eircode, phoneNumber, LocalDate.now());
                        newMember.setAdmin(0); // Default to non-admin
                        boolean isRegistered = true ;//MembersDao.insertMember(connection, newMember);

                        if (isRegistered) {
                            System.out.println("Registration successful!");
                            Auth = true;
                        } else {
                           System.out.println("Registration failed. Please try again.");
                        }
                    } else {
                        System.out.println("You are already logged in.");
                    }
                    break;
                case 2:
                   //login
                   // call the login and then set isAuthenticated to true
                    Auth= true;
                    break;
                case 3:
                    //view all books
                    BookDao booky = new BookDao("libraryapp");
                    try {
                        System.out.println(booky.findAllBooks());
                    }catch (DaoException e){
                        System.out.println("error displaying books");
                    }
                    break;
                case 4:
                    //view active loans
                    LoanDao loany = new LoanDao("libraryapp");
                    System.out.println("Please enter your member id to see current active book loans");
                    int memberID1 = scanner.nextInt();
                    try {
                        System.out.println(loany.viewActiveLoans(memberID1));
                    }catch (DaoException e){
                        System.out.println("Couldn't display your loans, sorry");
                    }
                    break;
                case 5:
                    //view all loans
                    LoanDao loanys = new LoanDao("libraryapp");
                    System.out.println("Please enter your member id to see all book loans");
                    int memID = scanner.nextInt();
                    try {
                        System.out.println(loanys.getLoansForMember(memID));
                    }catch (DaoException e){
                        System.out.println("Couldn't display your loans, sorry");
                    }
                    break;
                case 6:
                    //loan book
                    System.out.println("Welcome to my Library");

                    System.out.println("Enter your Member ID");
                    int memberID = scanner.nextInt();

                    System.out.println("Enter the Book ID you want to borrow homie");
                    int bookID = scanner.nextInt();

                    try {
                        LoanDao library = new LoanDao("library");
                        boolean isBorrowed = library.BorrowBook(memberID, bookID);
                        if (isBorrowed) {
                            System.out.println("Book successfully borrowed!");
                        } else {
                            System.out.println("Filed to borrow the book");
                        }

                    } catch (DaoException e) {
                        System.out.println("An error Ocurred"+ e.getMessage());
                    } finally {
                        scanner.close();
                    }
                    break;
                case 7:
                    // return book
                    System.out.println("Welcome to The Return Page Library");

                    System.out.println("Enter your Member ID:");
                    memberID = scanner.nextInt();

                    System.out.println("Enter your book ID amigio");
                    bookID = scanner.nextInt();

                    LoanDao library = new LoanDao("library");
                    try {
                        boolean isReturn = library.returnbook(memberID, bookID);

                        if (isReturn) {
                            System.out.println("Book was successfully returned");
                        } else {
                            System.out.println("Failed to return the book it has might be borrowed or late");
                        }
                    } catch (DaoException e) {
                        System.out.println("An error have been occurred:" + e.getMessage());
                    } finally {
                        scanner.close();
                    }


                    break;
                case 8:
                    //view late views
                    LoanDao loanyes = new LoanDao("libraryapp");
                    System.out.println("Too see if you have any fees for a book, please follow");
                    System.out.println("Please enter your member id ");
                    int memIDs = scanner.nextInt();
                    System.out.println("Please enter the book id");
                    int boookieID = scanner.nextInt();
                    if(!loanyes.isLate(memIDs, boookieID)){
                        System.out.println("you appear to have a fee for the book");
                        try {
                            System.out.println(loanyes.getLoansForMember(memIDs));
                        }catch (DaoException e){
                            System.out.println("Error showing loans");
                        }
                    }else{
                        System.out.println("You have no fee for that book");
                    }

                    break;
                case 9:

                    LoanDao loas = new LoanDao("libraryapp");

                    System.out.println("pay for fee");
                    System.out.println("Enter member id:");
                    int Memsid = scanner.nextInt();
                    System.out.println("please enter credit card number:");
                    String creditCardnum = scanner.nextLine();
                    System.out.println("please enter credit expiry date:");
                    String expiry = scanner.nextLine();

                    if(!loas.payLateFeeValidate(Memsid, creditCardnum, expiry)){
                        System.out.println(" card payment went through");
                    }else{
                        System.out.println("Please try again!");
                    }
                    break;
                case 10:
                    System.exit(0);
                    break;
                    case 11:
                    if (isAuthenticated.isAdmin()) {
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
