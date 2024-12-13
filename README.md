Sure, here is a basic structure for the documentation of your project:

# Simple Blog Application

## Overview
This project is a simple blog application built using Java, Spring Boot, and Thymeleaf. It allows users to post messages and view messages from other users in real-time.

## Technologies Used
- Java
- Spring Boot
- Thymeleaf
- Maven
- SLF4J (Logging)
- Spring Security (for authentication)

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/example/demo4/
│   │       ├── controller/
│   │       │   └── MainController.java
│   │       ├── messages/
│   │       │   ├── AsyncMessageService.java
│   │       │   ├── MessageEntity.java
│   │       │   ├── MessageRepository.java
│   │       │   └── MessageService.java
│   │       └── security/
│   │           └── WebSecurityConfig.java
│   ├── resources/
│   │   ├── templates/
│   │   │   └── index.html
│   │   └── application.properties
└── test/
    └── java/
        └── com/example/demo4/
            └── Demo4ApplicationTests.java
```

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6.0 or higher

### Installation
1. Clone the repository:
    ```sh
    git clone https: https://github.com/PVBonev/chat-app
    cd simple-blog
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

### Accessing the Application
Once the application is running, you can access it at `http://localhost:8080`.

## Features

### Posting Messages
Users can post messages by typing in the input field and clicking the "Send" button. The messages are saved to the database and displayed in the chat window.

### Viewing Messages
Messages are displayed in real-time in the chat window. The messages are fetched from the server every 3 seconds.

## Code Explanation

### `MainController.java`
Handles the main endpoints of the application:
- `GET /`: Loads the index page and displays all messages.
- `POST /send`: Handles the submission of new messages.

### `MessageService.java`
Provides methods to interact with the `MessageRepository` for saving and retrieving messages.

### `AsyncMessageService.java`
Provides asynchronous methods for saving messages to improve performance.

### `index.html`
The main HTML template for the application, using Thymeleaf for dynamic content rendering.

## Logging
The application uses SLF4J for logging. Logs are printed to the console with information about accessing pages and sending messages.

## Security
Spring Security is used to handle user authentication. Users must log in to post messages.

## License
This project is licensed under free and open-source software (FOSS) with no specific license.

# Application Workflow Documentation

## Step 1: Accessing the Application
- **URL**: `http://localhost:8080/`
- **Action**: The user navigates to the home page of the application.
- **Result**: Due to security, the user is redirected to the login page.

## Step 2: Registration
- **URL**: `http://localhost:8080/register`
- **Action**: The user clicks on the register link from the login form and is taken to the registration page.
- **Result**: The user fills out the registration form and submits it.

## Step 3: Email Confirmation
- **Action**: A popup informs the user to check their email.
- **Result**: The user visits their email and opens the new mail received. The email format is defined in the `buildEmail` method of the `RegistrationService` class. The email contains an "Activate Now" link.

## Step 4: Activating the Account
- **Action**: The user clicks the "Activate Now" link in the email.
- **Result**: The link takes the user to the `/registration/confirm` URL, which calls the `confirmToken` method from the `RegistrationService` class and redirects the user to the login page.

## Step 5: Token Confirmation
- **Method**: `confirmToken` in `RegistrationService`
- **Action**: This method performs checks and calls the `enableAppUser` method from the `AppUserService` class.

## Step 6: Enabling the User Profile
- **Method**: `enableAppUser` in `AppUserService`
- **Action**: This method calls the `enableAppUser` method from the `AppUserRepository` class, which executes a query to enable the user profile.

## Step 7: Logging In
- **Action**: The user enters their email and password on the login page.
- **Result**: Spring Security checks the credentials. If they are correct, the user is redirected to `/`, the main page of the website.

## Step 8: Loading Messages
- **Action**: The `index` method in `MainController` is called.
- **Result**: The `getAllMessages` method from `MessageService` is called, which in turn calls `findAll` from `MessageRepository` to retrieve all messages from the database.

## Step 9: Sending a Message
- **Action**: The user types a message and clicks the "Send" button (or presses Enter).
- **Result**:
   - The application retrieves the username, current time, and contents of the message.
   - The `saveMessage` method (either async or regular version) from `MessageService` is called, which in turn calls `addMessage` from `MessageRepository` to execute a query on the database and save the message.
   - The user is then redirected back to `/`.
## Summary
1. **Access URL**: Navigate to `http://localhost:8080/` and get redirected to `/login`.
2. **Register**: Click on register and go to `http://localhost:8080/register`, fill out the form, and submit.
3. **Check Email**: Receive an email with an activation link.
4. **Activate Account**: Click the activation link, which redirects to `/registration/confirm` and then to `/login`.
5. **Confirm Token**: `confirmToken` method checks the token and enables the user.
6. **Enable User**: `enableAppUser` method in `AppUserService` and `AppUserRepository` enables the user profile.
7. **Log In**: Enter email and password on the login page. If credentials are correct, get redirected to `/`.
8. **Load Messages**: `index` method in `MainController` calls `getAllMessages` from `MessageService`, which retrieves all messages from the database.
9. **Send Message**: Type a message and click "Send" (or press Enter). The application retrieves the username, current time, and message content, then calls `saveMessage` from `MessageService` to save the message to the database and redirects back to `/`.

## Screens
1. **Chat(main page)**:
![Screen3-chat](https://github.com/user-attachments/assets/5765f5ff-e08b-47a1-b3f3-af10d275e294)
2. **Signup**:
![Screen2-signup](https://github.com/user-attachments/assets/ce4d83c0-a726-4f93-a58b-643f8f5b318e)
3. **Login**:
![Screen1-login](https://github.com/user-attachments/assets/453524cc-358d-43bd-a8c9-3dae00f45b2c)

4. **Speed comparison between singlethreaded/multithreaded approach**:
![Screen4-terminal-comparison_speed](https://github.com/user-attachments/assets/d82b94ae-4ae3-4c94-88a2-a51ff94dc5f1)

