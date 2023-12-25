The program uses: List<Book> books, List<Member> members, and private Member loggedInMember.
The code defines a class named Library, which is the main component of the library management system.The constructor initializes the Library object by creating empty lists for storing books (books) and members (members). It also sets the loggedInMember to null, indicating that no member is currently logged in.
-Adding Books (addBook method) method allows you to add books to the library. It takes three parameters: title (the title of the book), author (the author of the book), and totalCopies (the number of copies of the book to add). For each copy, a unique bookId is assigned incrementally, and a new Book object is created and added to the books list. This way, the library maintains a collection of available books.

-Removing Books (removeBook method): You can remove a book from the library by specifying its bookId. The code uses an iterator to loop through the books list and find the book with the matching ID. Once found, it removes the book from the list. If the book is found and removed, it prints a success message; otherwise, it informs you that the book was not found.

-generating Member ID (generateMemberId method): This method generates unique member IDs

-Registering Members (registerMember method): You can register new library members using this method. It takes the member's name, age, and phoneNumber
-Removing Members (removeMember method): To remove a member from the library, you can provide their memberId

-Logging in as a Member (loginAsMember method): This method allows a member to log in by providing their name and phoneNumber. It searches through the members list to find a match. If a member with the provided name and phone number is found, it sets the loggedInMember to that member. If not, it informs you that the member was not found.

-Logging out (logout method): The logout method logs out the currently logged-in member by setting loggedInMember to null.

-Issuing Books (issueBook method): Members can borrow books using this method by specifying the bookId. It checks if a member is logged in, if the book is available, and sets a due date for the book. If the book is successfully issued, it prints a confirmation message.

-Returning Books (returnBook method): Members can return books using this method by specifying the bookId. It checks if a member is logged in, calculates fines (if any) for returning the book late, and updates the book's status as returned. If the book is returned successfully, it prints a confirmation message.

-Listing Books and Members (listBooks and listMembers methods): These methods display the available books and registered members, respectively, with relevant details.

-Viewing Member's Books and Fines (viewMyBooks, getTotalFineForMember, and payFine methods): These methods allow members to view their borrowed books, check fines, and pay fines if they have any.

-Viewing All Members with Books and Fines (viewAllMembersWithBooksAndFines method): This method lists all members along with their borrowed books and fines.

-Viewing All Books (viewAllBooks method): This method lists all available books in the library, including their IDs, titles, authors, and the total number of copies.

-Exiting Library (exitLibrary method): This method gracefully exits the library application when called.