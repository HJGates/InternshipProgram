# README

Payment Class

The Payment class is a Java program designed to determine who should pay based on a list of names, their orders, and the cost of each order. It also considers the last person who paid to avoid assigning them the payment again.

Usage

To use the Payment class, follow these steps:

Compile the Program: Compile the Payment.java file using the Java compiler.
javac Payment.java

Run the Program: Execute the compiled program with appropriate arguments.
java Payment orders.txt [last_person]


orders.txt: Specify the text file containing the list of names, orders, and costs. The format of each line should be <NAME>, <ORDER>: $<COST>.
last_person (Optional): Provide the name of the last person who paid, if applicable.
Note: If last_person is provided, they will be excluded from being assigned the payment.

Arguments

The program accepts one or two arguments:

Required Argument:

orders.txt: A text file containing the list of names, orders, and costs.
Optional Argument:

last_person: The name of the last person who paid. If provided, they will be excluded from being assigned the payment. If not provided, all names will be considered for payment assignment.
Error Handling

If the program is executed with an incorrect number of arguments (not 1 or 2), it will display an error message and exit.
If there's an error reading or writing the file specified, the program will output an error message indicating the issue.
Methods

getOrders(String fileName): Reads the orders from the specified file and returns a HashMap containing names and their respective order costs.

getMax(HashMap<String, Double> orders, String lastPayed): Determines the person who should pay based on the highest cost of order, excluding the last person who paid if provided.

Author

Henry Gates
Version 1.0
