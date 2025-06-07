# WaterMan Analysis


This repository contains a simple Java Swing application for ordering water cans, composed of two main components: `WaterContent.java` (the order page) and `Bill.java` (the bill generation and confirmation page).

## 1. WaterContent.java - Order Page

This Java program creates a graphical user interface (GUI) allowing users to place an order for water cans. It enables users to select the brand of water, the capacity, the expected delivery date, and the payment method.

### Features:
* **User Welcome:** Displays a welcome message to the logged-in user.
* **Water Can Selection:**
    * Choose between "Bisleri" and "Aqua Fina" can names.
    * Select capacity from "5L", "10L", and "20L".
* **Expected Date Input:** Allows the user to input the desired delivery date in "DD/MM/YYYY" format.
* **Payment Method:** Users can choose between "GPay" and "COD (Cash on Delivery)".
* **Confirmation:** A "Confirm" button to finalize the order.
* **Data Transfer:** Upon confirmation, the selected order details and user information are passed to the `Bill` class for further processing.

### How to Run:
1.  **Dependencies:** This program uses Java Swing, which is a standard part of the Java Development Kit (JDK).
2.  **Compilation:** Compile the `WaterContent.java` file using a Java compiler:
    ```bash
    javac WaterContent.java
    ```
3.  **Execution:** This class is typically instantiated by another part of the application that handles user login or initial setup, as its constructor requires user details. An example of how it might be called:
    ```bash
    java watercopy.WaterContent "JohnDoe" "john.doe@example.com" "mypass" "123 Main St" "9876543210"
    ```
  
## 2. Bill.java - Bill Generation and Order Confirmation

This Java Swing class is responsible for displaying a detailed order summary (bill) to the user and managing the confirmation or cancellation of the order. It also integrates with an external Python script for sending email notifications upon successful order confirmation.

### Features:
* **Bill Display:** Presents a detailed bill to the user, including:
    * Email address
    * Phone number
    * Delivery address
    * Expected delivery date
    * Selected can name (e.g., "Bisleri", "Aqua Fina")
    * Selected can capacity (e.g., "5L", "10L", "20L")
    * Payment mode (e.g., "GPay", "COD")
    * Calculated total amount
* **Automatic Amount Calculation:** Dynamically calculates the total bill amount. "Bisleri" is priced at ₹30 per liter, and "Aqua Fina" at ₹20 per liter. The calculation uses the numeric value of the selected capacity (e.g., 5 for "5L").
* **Order Confirmation:**
    * A "Confirm" button finalizes the order.
    * Upon confirmation, it attempts to execute an external Python script named `emailprog.py` to send an email notification with all relevant order details as command-line arguments.
    * Displays a "Successfully ordered" message upon successful execution.
    * Returns to the `WaterContent` order page after confirmation.
* **Order Cancellation:**
    * A "Cancel" button allows the user to discard the current order.
    * Returns the user to the `WaterContent` order page without processing the order.
* **Read-only Fields:** All order details displayed on the bill are non-editable by the user.
