# JUnitProject

Test Files

1. BookServiceTest.java
Purpose: Tests the functionality of the BookService class, which handles operations related to books, such as searching for books, purchasing books, and adding reviews.

Key Tests:
testSearchBook_Found: Verifies that books matching a keyword can be found.
testPurchaseBook_Success: Confirms that a book can be successfully purchased by a registered user.
testAddBookReview_Success: Ensures that a review can be added to a book that a user has purchased.

3. UserServiceTest.java
Purpose: Tests the functionality of the UserService class, which manages user registration, login, and profile updates.

Key Tests:
testRegisterUser_Success: Checks that a new user can be registered successfully.
testLoginUser_Success: Validates that a user can log in with the correct username and password.
testUpdateUserProfile_Success: Ensures that a user's profile can be updated with a new username, password, and email.

Running the Tests
To run the tests, ensure you have JUnit 4 and Mockito included in your project dependencies. You can run the tests using your IDE's built-in test runner or via the command line with Maven:

