package org.example;

import java.util.Date;
import java.util.concurrent.TimeUnit;

class Book {
    int bookId;
    String title;
    String author;
    int totalCopies;
    int availableCopies;
    double dues; // Added dues as an attribute
    private Date dueDate;
    int issuetime;

    public Book(int bookId, String title, String author, int totalCopies, int issuetime) {
        this.bookId= bookId;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.dues = 0.0; // Initialize dues to 0
        this.dueDate = null;
        this.issuetime = issuetime;

    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public double getDues() {
        return dues;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void issueBook() {
        if (availableCopies > 0) {
            availableCopies--;
            calculateDueDate();
        }
    }

    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            dueDate = null;
        }
    }

    private void calculateDueDate() {
        Date currentDate = new Date();
        long tenDaysInMillis = TimeUnit.DAYS.toMillis(10);
        long dueDateInMillis = currentDate.getTime() + tenDaysInMillis;
        dueDate = new Date(dueDateInMillis);
    }

}
