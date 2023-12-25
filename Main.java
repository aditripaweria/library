package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        int lChoice = 0;
        int mChoice = 0;

        System.out.println("Library Portal Initialized…");
        while (choice != 3) {
            System.out.println("---------------------------------");
            System.out.println("1. Enter as a librarian");
            System.out.println("2. Enter as a member");
            System.out.println("3. Exit");
            System.out.println("---------------------------------");
            System.out.print("");
            choice = scanner.nextInt();

            if (choice == 1) {
                while (lChoice != 7) {
                    System.out.println("---------------------------------");
                    System.out.println("1. Register a member");
                    System.out.println("2. Remove a member");
                    System.out.println("3. Add a book");
                    System.out.println("4. Remove a book");
                    System.out.println("5. View all members along with their books and fines to be paid");
                    System.out.println("6. View all books");
                    System.out.println("7. Back");
                    System.out.println("---------------------------------");
                    System.out.print("");
                    lChoice = scanner.nextInt();

                    if (lChoice == 1) {
                        scanner.nextLine();
                        System.out.println("Enter member's name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter member's age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter member's phone number: ");
                        String phoneNumber = scanner.nextLine();
                        library.registerMember(name, age, phoneNumber);
                    } else if (lChoice == 2) {
                        scanner.nextLine();
                        System.out.print("Enter member ID: ");
                        String memberId = scanner.nextLine();
                        library.removeMember(memberId);
                    } else if (lChoice == 3) {
                        scanner.nextLine();
                        System.out.print("Enter book title: ");
                        String bookTitle = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String bookAuthor = scanner.nextLine();
                        System.out.print("Enter number of copies: ");
                        int numCopies = scanner.nextInt();
                        library.addBook(bookTitle, bookAuthor, numCopies);
                    } else if (lChoice == 4) {
                        scanner.nextLine();
                        System.out.print("Enter book ID to remove: ");
                        int bookId = scanner.nextInt();
                        library.removeBook(bookId);
                    } else if (lChoice == 5) {
                        library.viewAllMembersWithBooksAndFines();
                    } else if (lChoice == 6) {
                        library.viewAllBooks();
                    }
                }
                lChoice = 0;
            } else if (choice == 2) {
                scanner.nextLine();
                System.out.println("---------------------------------");
                System.out.println("Enter member's name: ");
                String name = scanner.nextLine();
                System.out.println("Enter member's phone number: ");
                String phoneNumber = scanner.nextLine();
                System.out.println("---------------------------------");
                library.loginAsMember(name, phoneNumber);

                while (mChoice != 6) {
                    System.out.println("---------------------------------");
                    System.out.println("1. List Available Books");
                    System.out.println("2. List My Books");
                    System.out.println("3. Issue Book");
                    System.out.println("4. Return Book");
                    System.out.println("5. Pay Fine");
                    System.out.println("6. Back");
                    System.out.println("---------------------------------");
                    System.out.print("");
                    mChoice = scanner.nextInt();

                    if (mChoice == 1){
                        library.listBooks();

                    }
                    if (mChoice == 2){
                        library.viewMyBooks();
                    }
                    if (mChoice == 3){
                        scanner.nextLine();
                        System.out.println("---------------------------------");
                        System.out.println("Book Id: ");
                        int bookId = scanner.nextInt();
                        System.out.println("---------------------------------");
                        library.issueBook(bookId);
                        LocalTime currentTime = LocalTime.now();
                        int minutes = currentTime.getMinute();
                        int second = currentTime.getSecond();
                        int t = minutes*60 + second;

                    }
                    if (mChoice == 4){
                        scanner.nextLine();
                        System.out.println("---------------------------------");
                        System.out.println("Enter Book ID");
                        int bookId = scanner.nextInt();
                        System.out.println("---------------------------------");
                        library.returnBook(bookId);
                    }
                    if (mChoice == 5){
                        library.payFine();
                    }
                }
                mChoice = 0; // Reset mChoice for the next iteration
            } else if (choice == 3) {
                library.exitLibrary();
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        scanner.close();
    }
}
