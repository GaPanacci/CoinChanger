# Coins Change Calculator

This Java program is designed to enumerate the distinct ways in which a given amount of money, in the units of currency in any given coin denomination system, can be changed into various types of coins within that system. It allows users to specify a set of coin denominations and names, and then calculates all the possible combinations of those coins that sum up to a given amount.

## Features

- **Set Currency:** Users can input coin names and their corresponding values in ascending order. The input ends when the user types "STOP".
- **Set Amount:** Users are prompted to enter the amount of money to be changed into coins. The amount should be entered in the lowest denominational value of the currency system being used.
- **Calculate Combinations:** The program calculates and prints out all the distinct ways the entered amount can be made up using the specified coin denominations.

## Usage

1. **Setting Up the Currency System:** When the program runs, it first asks the user to enter the names and values of different coins in ascending order. Each coin name and its value are entered one at a time, followed by the user inputting "STOP" to indicate completion.

2. **Entering the Amount to Change:** After setting up the currency system, the user is then prompted to enter the amount of money they wish to change. The program ensures this amount is greater than 0.

3. **Calculating and Displaying Results:** Once the currency system and amount have been set, the program calculates all possible combinations of the specified coins that can make up the given amount. It then outputs these combinations, alongside a count of each type of coin used in every combination.

## Implementation Details

- The main functionality is divided into multiple methods for modularity.
- It uses recursion to explore all possible combinations of coin usage.
- Ensures user inputs are validated before processing.
- Dynamically adjusts coin names for singular and plural forms based on the context.

## Running the Program

To run this program:

1. Compile the Java code using a Java compiler (e.g., `javac Coins.java`).
2. Run the compiled class file with Java Runtime Environment (e.g., `java Coins`).
3. Follow the on-screen prompts to input coin denominations and the amount to change.

This program is an excellent tool for understanding the concepts of recursion and backtracking, as well as for practicing with Java Collections.
