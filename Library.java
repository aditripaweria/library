package org.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.time.LocalTime;

public class Library {
    List<Book> books;
    List<Member> members;
    private Member loggedInMember;
    private int memberCounter = 1;
    private int count = 0;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loggedInMember = null;

    }

    public void addBook(String title, String author, int totalCopies) {
        for (int i = 0; i < totalCopies; i++) {
            count++;
            int bookId = count;
            // Book.bookId = count;
            Book newBook = new Book(bookId, title, author, totalCopies, totalCopies); // Initialize availableCopies with totalCopies
            books.add(newBook);
        }
    }



    public void removeBook(int bookId) {
        Iterator<Book> iterator = books.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookId() == bookId) {
                iterator.remove(); // Remove the book with matching bookId
                found = true;
                break; // No need to continue iterating once the book is found and removed
            }
        }
        if (found) {
            System.out.println("Book with ID " + bookId + " has been removed from the library.");
        } else {
            System.out.println("Book with ID " + bookId + " not found in the library.");
        }
    }


    public String generateMemberId() {

        String memberId = "MEM" + memberCounter;
        memberCounter++;
        return memberId;
    }

    public void registerMember(String name, int age, String phoneNumber) {
        for (Member existingMember : members) {
            if (existingMember.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("A member with the same phone number already exists.");
                return;
            }
        }
        String memberId = generateMemberId();
        Member newMember = new Member(name, age, phoneNumber);
        members.add(newMember);
        System.out.println("Member registered successfully.");
    }



    public void removeMember(String memberId) {
        Iterator<Member> iterator = members.iterator();
        while (iterator.hasNext()) {
            Member member = iterator.next();
            if (member.getMemberId().equals(memberId)) {
                iterator.remove(); // Remove the member with matching memberId
                return;
            }
        }
        System.out.println("Member with ID " + memberId + " not found in the library.");
    }

    public void loginAsMember(String name, String phoneNumber) {
        // Check if a member with the provided name and phone number exists
        for (Member member : members) {
            if (member.getName().equals(name) && member.getPhoneNumber().equals(phoneNumber)) {
                loggedInMember = member;
                System.out.println("Logged in as " + loggedInMember.getName());
                return;
            }
            else{
                System.out.println("Member not found. Please check your name and phone number.");
            }
        }

    }

    public void logout() {
        loggedInMember = null;
        System.out.println("Logged out.");
    }


    /*public void issueBook(String bookId) {
        if (loggedInMember == null) {
            System.out.println("Please log in as a member before issuing a book.");
            return;
        }

        double fines = calculateFine(bookId);
        if (fines > 0) {
            System.out.println("You have pending fines of Rs. " + fines + ". Clear your fines.");
            return;
        }

        Book bookToIssue = null;
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                bookToIssue = book;
                break;
            }
        }

        if (bookToIssue == null) {
            System.out.println(bookId + " not found in the library.");
        } else if (bookToIssue.getAvailableCopies() <= 0) {
            System.out.println("No available copies of the book for borrowing.");
        } else {
            loggedInMember.borrowBook(bookToIssue);
            bookToIssue.issueBook();
            System.out.println("Book with ID " + bookId + " has been issued to you.");
        }
    }*/

    /*public void issueBook(int bookId) {
        if (loggedInMember == null) {
            System.out.println("Please log in as a member before issuing a book.");
            return;
        }


        Book bookToIssue = null;
        for (Book book : books) {
            if (book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
                bookToIssue = book;
                break;
            }
        }

        if (bookToIssue == null) {
            System.out.println(bookId + " not found in the library.");
        } else if (bookToIssue.getAvailableCopies() <= 0) {
            System.out.println("No available copies of the book for borrowing.");
        } else {
            loggedInMember.borrowBook(bookToIssue);
            bookToIssue.issueBook();
            System.out.println("Book with ID " + bookId + " has been issued to you.");
        }
    }*/

    public void issueBook(int bookId) {
        if (loggedInMember == null) {
            System.out.println("Please log in as a member before issuing a book.");
            return;
        }

        Book bookToIssue = findBookById(bookId);

        if (bookToIssue == null) {
            System.out.println("Book with ID " + bookId + " not found in the library.");
        } else if (bookToIssue.getAvailableCopies() <= 0) {
            System.out.println("No available copies of the book for borrowing.");
        } else {
            LocalTime currentTime = LocalTime.now();
            int minutes = currentTime.getMinute();
            int second = currentTime.getSecond();
            int t = minutes*60 + second;
            for ( Book b: books ){
                if (b.bookId == bookId) {
                    b.issuetime = t;
                }
            }

            System.out.println("Book with ID " + bookId + " has been issued to you.");
        }
    }



    /*public void returnBook(int bookId) {
        // Check if a member is logged in
        if (loggedInMember == null) {
            System.out.println("Please log in as a member before returning a book.");
            return;
        }

        // Find the book with the specified bookId
        Book bookToReturn = null;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                bookToReturn = book;
                break;
            }
        }
        if (bookToReturn == null) {
            System.out.println("Book with ID " + bookId + " not found in the library.");
        } else if (!loggedInMember.hasBorrowedBook(bookToReturn)) {
            System.out.println("You have not borrowed the book with ID " + bookId + ".");
        } else {
            // Calculate and display any applicable fines (if the book is returned late)
            double fines = calculateFine(bookId);
            if (fines > 0) {
                System.out.println("You have fines of Rs. " + fines + " for returning the book late.");
                System.out.println("Please pay the fines before completing the return.");
            } else {
                loggedInMember.returnBook(bookToReturn);
                bookToReturn.returnBook();
                System.out.println("Book with ID " + bookId + " has been successfully returned.");
            }
        }
    }*/


    public void returnBook(int bookId) {
        if (loggedInMember == null) {
            System.out.println("Please log in as a member before returning a book.");
            return;
        }

        Book bookToReturn = findBookById(bookId);

        if (bookToReturn == null) {
            System.out.println("Book with ID " + bookId + " not found in the library.");
        } else {
            LocalTime currentTime = LocalTime.now();
            int minutes = currentTime.getMinute();
            int second = currentTime.getSecond();
            int t2 = minutes*60 + second;
            int t1= 0;


            for ( Book b: books ){
                if (b.availableCopies > 0) {
                    if (b.bookId == bookId) {
                        t1 = b.issuetime;
                        break;
                    }
                }
            }
            int days = t2-t1;


            if (days > 10) {
                System.out.println("You have fines of Rs. " + (days*3) + " for returning the book late.");
                System.out.println("Please pay the fines before completing the return.");
            }
            else {
                loggedInMember.returnBook(bookToReturn);
                bookToReturn.returnBook();
                books.add(bookToReturn);

                System.out.println("Book with ID " + bookId + " has been successfully returned.");
            }
        }
    }




    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book != null && book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }



    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books are available.");
        } else {
            System.out.println("Available Books in the Library:");
            for (Book book : books) {
                if (book.getAvailableCopies() > 0) {
                    System.out.println("Book ID: " + book.getBookId());
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Available Copies: " + book.getAvailableCopies());
                    System.out.println();
                }
            }
        }
    }

    public void listMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            System.out.println("Registered Members in the Library:");
            for (Member member : members) {
                System.out.println("Member ID: " + member.getMemberId());
                System.out.println("Name: " + member.getName());
                System.out.println("Age: " + member.getAge());
                System.out.println("Phone Number: " + member.getPhoneNumber());
                System.out.println();
            }
        }
    }

    private Date calculateDueDate(Date issueDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issueDate);
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        return calendar.getTime();
    }




    public double getTotalFineForMember() {
        if (loggedInMember != null) {
            return loggedInMember.getDues();
        }
        return 0.0;
    }

    public void payFine() {
        double totalFine = getTotalFineForMember();
        if (totalFine > 0) {
            loggedInMember.clearDues();
            System.out.println("You had a total fine of Rs. " + totalFine + ". It has been paid successfully!");
        } else {
            System.out.println("You don't have any fines to pay.");
        }
    }


    public void viewAllMembersWithBooksAndFines() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        System.out.println("Members and Their Borrowed Books:");
        for (Member member : members) {
            System.out.println("Member ID: " + member.getMemberId());
            System.out.println("Name: " + member.getName());
            System.out.println("Age: " + member.getAge());
            System.out.println("Phone Number: " + member.getPhoneNumber());

            List<Book> borrowedBooks = member.getBorrowedBooks();
            if (borrowedBooks.isEmpty()) {
                System.out.println("No books borrowed.");
            } else {
                System.out.println("Borrowed Books:");
                for (Book book : borrowedBooks) {
                    System.out.println("Book ID: " + book.getBookId());
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Author: " + book.getAuthor());
                }
            }
            System.out.println();
        }
    }

    public void viewMyBooks() {
        if (loggedInMember == null) {
            System.out.println("Please log in as a member to view your books.");
            return;
        }

        System.out.println("Books Borrowed by " + loggedInMember.getName() + ":");
        List<Book> borrowedBooks = loggedInMember.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("You have not borrowed any books.");
        } else {
            for (Book book : borrowedBooks) {
                System.out.println("Book ID: " + book.getBookId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println();
            }
        }
    }



    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("List of all available books:");
            for (Book book : books) {
                System.out.println("Book ID: " + book.getBookId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Total Copies: " + book.getTotalCopies());
                System.out.println("-----------------------------");
            }
        }
    }

    public void exitLibrary() {
        System.out.println("Exiting the library application.");
        System.exit(0);
    }
}