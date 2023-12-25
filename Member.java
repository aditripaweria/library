package org.example;

import java.util.ArrayList;
import java.util.List;

class Member {
    private String memberId;
    private String name;
    private int age;
    private double dues;
    private String phoneNumber;
    private List<Book> borrowedBooks;


    private static int memberCounter = 1;
    private double due = 0.0;

    public Member(String name, int age, String phoneNumber) {
        this.memberId = generateMemberId();
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.borrowedBooks = new ArrayList<>();
    }

    public String generateMemberId() {

        String memberId = "MEM" + memberCounter;
        memberCounter++;
        return memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getDues() {
        return dues;
    }

    public void addToDue(double amount) {
        // Add an amount to the member's dues
        due += amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public boolean hasBorrowedBook(Book book) {
        return borrowedBooks.contains(book);
    }

    public void calculateDues() {
        dues = 0.0; // Initialize dues to 0
        for (Book book : borrowedBooks) {

            dues += calculateFine(book);
        }
    }

    private double calculateFine(Book book) {
        double fine = 0.0;
        return fine;
    }


    public void clearDues() {
        due = 0.0;
    }
}
