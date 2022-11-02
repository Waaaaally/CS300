//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Shopping Cart Assignment
// Course:   CS 300 Spring 2022
//
// Author:   Pritish Das
// Email:    pdas26@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    No Pair Programming
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a shopping cart with features allowing you to search through a set catalogue
 * using IDs or item names, add and remove items, check if the cart has a specific item as well as
 * its frequency, and get the total price of the cart and a general summary.
 * @author Pritish Das
 * @version 1.0
 */
public class ShoppingCart {
    private static final double TAX_RATE = 0.05; //Sales Tax

    /* MarketItems: a perfect-size two-dimensional array that stores the list of
     available items in a given market
     MarketItems[i][0] refers to a String representation of the item identifiers
     MarketItems[i][1] refers the item name. Item names are also unique
     MarketItems[i][2] a String representation of the unit price
     of the item in dollars
     */
    private static String[][] marketItems =
            new String[][]{{"4390", "Apple", "$1.59"},
                    {"4046", "Avocado", "$0.59"}, {"4011", "Banana", "$0.49"},
                    {"4500", "Beef", "$3.79"}, {"4033", "Blueberry", "$6.89"},
                    {"4129", "Broccoli", "$1.79"}, {"4131", "Butter", "$4.59"},
                    {"4017", "Carrot", "$1.19"}, {"3240", "Cereal", "$3.69"},
                    {"3560", "Cheese", "$3.49"}, {"3294", "Chicken", "$5.09"},
                    {"4071", "Chocolate", "$3.19"}, {"4363", "Cookie", "$9.5"},
                    {"4232", "Cucumber", "$0.79"}, {"3033", "Eggs", "$3.09"},
                    {"4770", "Grape", "$2.29"}, {"3553", "Ice Cream", "$5.39"},
                    {"3117", "Milk", "$2.09"}, {"3437", "Mushroom", "$1.79"},
                    {"4663", "Onion", "$0.79"}, {"4030", "Pepper", "$1.99"},
                    {"3890", "Pizza", "$11.5"}, {"4139", "Potato", "$0.69"},
                    {"3044", "Spinach", "$3.09"}, {"4688", "Tomato", "$1.79"},
                    null, null, null, null};

    /**
     * Looks up an item in the market items using a given name
     * @param name name of market item to lookup in market items array
     * @return information regarding intended item (ID, Name, Price) as a String
     */
    public static String lookupProductByName(String name) {
        int marketItemsLength = marketItems.length;

        for (int i = 0; i < marketItemsLength; i++) {
            //commenting once since used a lot, short circuit logic to avoid null entries
            if ((marketItems[i] != null) && (marketItems[i][1].equals(name))) {
                return marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
            }
        }
        return "No match found";
    }

    /**
     * Looks up an item in the market items using a given id
     * @param id identifier of the product or item to search
     * @return information regarding intended item (ID, Name, Price) as a String
     */
    public static String lookupProductById(int id) {
        int marketItemsLength = marketItems.length;

        for (int i = 0; i < marketItemsLength; i++) {
            if ((marketItems[i] != null) && Integer.parseInt(marketItems[i][0]) == id) {
                return marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
            }
        }
        return "No match found";
    }

    /**
     * Gets the price of a specified product in dollars
     * @param name name of item to get price from
     * @return price of specified item as a double (-1 if not found).
     */
    public static double getProductPrice(String name) {
        double productPrice = 0;
        int marketItemsLength = marketItems.length;

        for (int i = 0; i < marketItemsLength; i++) {
            if ((marketItems[i] != null) && marketItems[i][1].equals(name)) {
                productPrice = Double.parseDouble(marketItems[i][2].substring(1));
                //substring starts @ index 1 to ignore the "$" before parseDouble
                return productPrice;
            }
        }
        return -1;
    }

    /**
     * Creates a 2d copy of the market items array
     * @return a 2d deep copy of market items array with a different reference
     */
    public static String[][] getCopyOfMarketItems() {
        int marketItemsLength = marketItems.length;
        String[][] marketCopy = new String[marketItemsLength][];
        //not full initializing the 2d array to avoid multiple null values at the end
        for (int i = 0; i < marketCopy.length; i++) {
            if (marketItems[i] != null) {
                marketCopy[i] = new String[marketItems[i].length]; //generalized 2nd layer array
                for (int j = 0; j < marketItems[i].length; j++) {
                    marketCopy[i][j] = marketItems[i][j];
                }
            } else {
                marketItems[i] = null; //the null entries will stay an entry instead of multiple
                //might be redundant I don't quite remember. probably yes bc String is object
            }
        }
        return marketCopy;
    }

    /**
     * Appends a single item to the shopping cart at the end
     * @param item name of the item being added
     * @param cart array to hold the added item
     * @param size total number of items in the cart
     * @return the new size of cart after adding an item
     */
    public static int addItemToCart(String item, String[] cart, int size) {
        if (cart.length <= size) {
            System.out.println("Can't add " + item + " to cart because it's full");
            return size;
        }

        for (int i = 0; i < cart.length; i++) {
            if (cart[i] == null) { //empty spots @ initialization are null in a sequence
                cart[i] = item;
                size++;
                return size;
            }
        }
        return size; //if nothing happened, size stays the same if full cart
    }

    /**
     * Checks the frequency of a specified item
     * @param item name of item's frequency being checked
     * @param cart array containing items
     * @param size total number of items in the cart
     * @return total occurences of the item as an int
     */
    public static int nbOccurrences(String item, String[] cart, int size) {
        int occurences = 0; //occurences are always @ least 0

        for (int i = 0; i < size; i++) {//go till size bc that's max items in cart.
            if (cart[i] != null && cart[i].equals(item)) {
                occurences++;
            }
        }
        return occurences;
    }

    /**
     * checks if a specified array is in the cart
     * @param item name of item being checked
     * @param cart array with items in it
     * @param size total number of items in the cart
     * @return true if item is in the cart, else false
     */
    public static boolean contains(String item, String[] cart, int size) {
        for (int i = 0; i < size; i++) {
            if (cart[i] != null && cart[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gathers total price of items in cart including sales tax
     * @param cart array holding items
     * @param size total items in the cart
     * @return total price as a double
     */
    public static double checkout(String[] cart, int size) {
        double preTaxTotal = 0;
        for (int i = 0; i < size; i++) {
            preTaxTotal += getProductPrice(cart[i]);
        }
        double taxTotal = preTaxTotal + preTaxTotal * TAX_RATE;
        taxTotal = (int)(taxTotal*100.0)/100.0;
        /*
        taxTotal can be a double w/ more than 2 decimal places
        multiplied by 100 to move decimal right 2 places.
        int cast to round off decimals even further right
        divide by 100.0 for double division & to move decimal point left reversing early op.
        */
        return (taxTotal);
    }

    /**
     * Removes the first instance of a specified item from cart
     * @param cart array that holds items
     * @param item specified item by name to remove
     * @param size total items in the cart
     * @return new size as an int after removal
     */
    public static int removeItem(String[] cart, String item, int size) {

        for (int i = 0; i < size; i++) {
            if (cart[i] != null && cart[i].equals(item)) {
                cart[i] = null;
                cart[i] = cart[size - 1]; //moving the last element of cart into new empty spot
                cart[size - 1] = null; //since the item has "moved" we make its old place null
                return (size - 1);
            }
        }

        return size;
    }


    /**
     * Clears the cart of all items
     * @param cart array holding the items
     * @param size total items in the cart
     * @return the size after cleared cart as an int
     */
    public static int emptyCart(String[] cart, int size) {
        for (int i = 0; i < cart.length; i++) {
            cart[i] = null;
        }
        return 0;//if everything is null, there is nothing & the size is 0.
    }

    /**
     * Gives a summary of all the items in a cart and their frequency
     * @param cart array holding the items
     * @param size total number of items in the cart
     * @return a string representation of all items and their frequency
     */
    public static String getCartSummary(String[] cart, int size) {
        String summary = ""; //empty string for an empty cart
        int occurences = 0; //all occurences at least 0
        for (int i = 0; i < size; i++) {
            occurences = nbOccurrences(cart[i], cart, size); //tracked per item incase unique
            if (cart[i] != null && summary.indexOf(cart[i]) == -1) //looks for if item is unique
                summary += "(" + occurences + ") " + cart[i] + "\n";
        }
        return summary;
    }
}
