
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The purpose of this class is to enumerate the distinct ways in which the given amount of money in the units of
 * currency in any given coin denomination system can be changed into the various coin types of that system.
 *
 * The main method runs some tests.
 *
 */

public class Coins {

    // Initialization of lists to store input of coin names and their respective values.
    private static List<Integer> coinValueList = new ArrayList<>();
    private static List<String> coinNameList = new ArrayList<>();
    private static int money;
    private static int counter = 0;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * This method prompts the user to set the operating currency by entering coin names and values.
     * The user is prompted to enter the coin values in ascending order.
     * When done the user can enter STOP to finish.
     */

    public static void setCurrency(){
        boolean entering = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter coin names and corresponding values in ascending order (type STOP when done):");

        while(entering) { // Loop to prompt user to enter coin name and value
            System.out.print("Enter the name of the coin (e.g., 'Quarter' or 'STOP' to finish): ");
            String coinName = scanner.next();

            if (coinName.equalsIgnoreCase("STOP")){ // Loop exiting condition i.e. the user is done.
                entering = false;
            }
            else{
                int coinValue;

                while (true){
                    System.out.print("Enter the value of the coin in the lowest denomination (e.g., '25' for 25 cents): ");
                    coinValue = scanner.nextInt();
                    if(coinValue > 0){ // Check to ensure input is valid
                        break;
                    }
                    else{
                        System.out.println("Coin value must be a positive integer.");
                    }
                }
                coinNameList.add(0, coinName); // Add coin name to list
                coinValueList.add(0, coinValue); // Add coin value to list
            }
        }
    }

    /**
     * This method prompts the user to set the amount to be changed in the chosen currency.
     * The user is prompted to enter the amount in the lowest denominational value.
     */
    public static void setAmount(){
        Scanner scanner = new Scanner(System.in);
        String coinName = coinNameList.get(coinNameList.size() - 1);

        if(coinValueList.get(coinValueList.size() - 1) == 1){ // Renaming function for single value coins
            String coinNameCut = coinName.substring(0,coinName.length()-1);
            coinName = coinNameCut + "ies"; // Make coin name plural
        }
        else if (coinValueList.get(coinValueList.size() - 1) != 1) { // Renaming function for multi value coins
            coinName = coinName + "s"; // Make coin name plural

        }
        System.out.print("Enter an amount in " + coinName + ": "); // Prompt user to enter amount to be changed.
        money = scanner.nextInt();
        while (money <= 0){ // Check to ensure input is valid.
            System.out.println("Amount must be greater than 0.");
            System.out.print("Enter an amount in " + coinNameList.get(0) + ": ");
            money = scanner.nextInt();
        }
    }
    /**
     * This method enumerates the distinct ways in which the given amount can be changed.
     * @param money The amount of money to change.
     * @return The number of ways the money can be changed
     */
    public static int ways(int money){
        return waysCombinationHelper(money, 0, "");
    }

    /**
     * This method is a recursive helper function to ways() which calculates the number of ways the given amount can be changed.
     * @param remaining The remaining amount to make.
     * @param coinIndex The current coin index.
     * @param currentCombination The current combination of coins.
     * @return The number of ways the money can be changed
     */
    private static int waysCombinationHelper(int remaining, int coinIndex, String currentCombination){
        if (remaining == 0) { //Money to be changed
            Output(currentCombination);
            return 1; // Valid way to make change found
        } else if (remaining < 0 || coinIndex >= coinValueList.size()) {
            return 0; // No valid way to make change
        } else {
            // Calculate number of ways to make change WITH current coin included.
            int waysWithCurrentCoin = waysCombinationHelper(remaining - coinValueList.get(coinIndex), coinIndex, currentCombination + coinNameList.get(coinIndex) + " ");
            // Calculate number of ways to make change WITHOUT current coin included.
            int waysWithoutCurrentCoin = waysCombinationHelper(remaining, coinIndex + 1, currentCombination);
            // Return sum of the ways with and without current coin
            return waysWithCurrentCoin + waysWithoutCurrentCoin;
        }
    }

    /**
     * This method is a recursive helper method to count the number of each coin in the current combination of coins.
     *
     * @param currentCombination The current combination of coins
     * @param coinIndex The index of the coin to count.
     * @return The count of the specified coin.
     */
    private static int wayCoinCountHelper(String currentCombination, int coinIndex){

        if (currentCombination.isEmpty()){
            return 0;
        }
        // Find index of current coin.
        int index = currentCombination.indexOf(coinNameList.get(coinIndex));

        // If coin found calculate ways to make change.
        if (index != -1){
            return 1 + wayCoinCountHelper(currentCombination.substring(index + coinNameList.get(coinIndex).length()), coinIndex);
        }
        else {
            return 0;
        }
    }

    /**
     * Method to output ways the money can be changed.
     * @param currentCombination The current combination of coins.
     */
    private static void Output(String currentCombination) {
        if (counter == 0) { // Display the first combination
            System.out.println("This amount can be changed in the following ways:");
        }

        counter++; // Keeping track of number of combinations

        System.out.print(counter + ") "); // Display combinations number

        for (int i = 0; i < coinValueList.size(); i++) {
            int count = wayCoinCountHelper(currentCombination, i); //Get count of current coin
            String coinName = coinNameList.get(i); //Get name of current coin

            if (count > 0) {
                if(count > 1){
                    // Pluralize coin names with value 1 to end in "ies" or add "s" for other values
                    if (coinValueList.get(i) == 1) {
                        coinName = coinName.substring(0, coinName.length() - 1) + "ies";
                    } else {
                        coinName = coinName + "s";
                    }

                }
                System.out.print(count + " " + coinName);

                // Add commas and spaces between coin counts.
                int j = i + 1;
                while (j < coinValueList.size() && wayCoinCountHelper(currentCombination, j) == 0) {
                    j++;
                }

                if (j < coinValueList.size()) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println();
    }


    public static void main(String[] args){
        setCurrency();
        setAmount();
        ways(money);
        scanner.close();
    }
}
