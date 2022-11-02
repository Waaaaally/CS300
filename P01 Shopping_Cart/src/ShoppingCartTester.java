public class ShoppingCartTester {
    public static void main(String[] args) {
        System.out.println("runAllTests(): " + runAllTests());
    }

    /**
     * Checks whether ShoppingCart.lookupProductByName() and
     * ShoppingCart.lookupProductById() methods work as expected.
     *
     * @return true when this test verifies a correct functionality,
     * and false otherwise
     */
    public static boolean testLookupMethods() {
        // define test scenarios.
        // 1. The item to find is at index 0 of the marketItems array
        String expectedOutput = "4390 Apple $1.59";
        if (!ShoppingCart.lookupProductByName("Apple").equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductByName() method "
                    + "failed to return the expected output when passed Apple as input");
            return false;
        }

        if (!ShoppingCart.lookupProductById(4390).equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductById() method "
                    + "failed to return the expected output when passed the id "
                    + "of Apple as input");
            return false;
        }

        // 2. The item to find is at the last non-null position of
        // the marketItems array
        expectedOutput = "4688 Tomato $1.79";
        if (!ShoppingCart.lookupProductByName("Tomato").equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductByName() method "
                    + "failed to return the expected output when passed Apple as input");
            return false;
        }
        if (!ShoppingCart.lookupProductById(4688).equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductById() method "
                    + "failed to return the expected output when passed the id "
                    + "of Tomato as input");
            return false;
        }

        // 3. The item to find is at an arbitrary position of the
        // middle of the marketItems array
        expectedOutput = "4363 Cookie $9.5";
        if (!ShoppingCart.lookupProductByName("Cookie").equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductByName() method "
                    + "failed to return the expected output when passed Cookie as input");
            return false;
        }
        if (!ShoppingCart.lookupProductById(4363).equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductById() method "
                    + "failed to return the expected output when passed the id "
                    + "of Cookie as input");
            return false;
        }

        // 4. The item to find is not found in the market
        expectedOutput = "No match found";

        if (!ShoppingCart.lookupProductByName("NOT FOUND").equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductByName() method "
                    + "failed to return the expected output when passed the name of "
                    + "a product not found in the market.");
            return false;
        }
        if (!ShoppingCart.lookupProductById(1000).equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductById() method "
                    + "failed to return the expected output when passed the identifier"
                    + "of a product not found in the market.");
            return false;
        }
        // You may add other test scenarios
        return true; // NO BUGS detected by this tester method
    }

    /**
     * Checks the correctness of ShoppingCart.getProductPrice() method
     * @return true when this test verifies a correct functionality,
     * and false otherwise
     */
    public static boolean testGetProductPrice() {

        double expectedPrice = 1.59; // price of the product Apple in the market

        // Two variables a and b of type double are equal if the absolute
        // value of their difference is less or equal to a small threshold epsilon.
        // For instance, if Math.abs(a - b) <= 0.001, then a equals b

        // 1. Apple which is @ Index 0 of the marketItems array.
        if (Math.abs(ShoppingCart.getProductPrice("Apple") - expectedPrice) > 0.001) {
            System.out.println("Problem detected: Your getProductPrice method "
                    + "failed to return the expected output when passed Apple as input");
            return false;
        }
        //2. Tomato which is @ the last non null index
        expectedPrice = 1.79;
        if (Math.abs(ShoppingCart.getProductPrice("Tomato") - expectedPrice) > 0.001) {
            System.out.println("Problem detected: Your getProductPrice method "
                    + "failed to return the expected output when passed Tomato as input");
            return false;
        }

        //3. An item that isn't found in the market
        expectedPrice = -1;
        if (Math.abs(ShoppingCart.getProductPrice("Not Found") - expectedPrice) > 0.001) {
            System.out.println("Problem detected: Your getProductPrice() method "
                    + "failed to return the expected output when passed the name of "
                    + "a product not found in the market.");
            return false;
        }

        return true; // No bug detected. The ShoppingCart.getProductPrice() passed this tester.
    }

    // This tester method checks the correctness of addItemToCart,
    // contains, and nbOccurrences methods defined in the ShoppingCart class

    /**
     * Checks the correctness of the addItemToCart(), contains(), and nbOccurrences()
     * static methods of class ShoppingCart.
     * @return true when this test verifies a correct functionality,
     * and false otherwise
     */
    public static boolean testAddItemToCartContainsNbOccurrences() {

        //1. AddItemToCart empty;
        String inputItem1 = "Banana";
        String[] cart = new String[3]; //empty cart
        int size = 0;
        int expected = 1;
        if (ShoppingCart.addItemToCart(inputItem1, cart, size) != expected) {
            System.out.println("Problem detected: Your addItemToCart method "
                    + "failed to return the expected output "
                    + "when adding an item to an empty cart.");
            System.out.print("The actual cart was: {");
            for (String items : cart) {
                System.out.print(items + " ");
            }
            System.out.println("}");
            System.out.println("The expected cart was {Banana, null, null}");
            return false;
        }

        //2. AddItemToCart full
        String[] cartFull = new String[]{"Milk", "Apple", "Banana", "Pizza"};
        size = 4; //full array (size == cart.length());
        expected = 4;
        if (ShoppingCart.addItemToCart(inputItem1, cartFull, size) != expected) {
            System.out.println("Problem detected: Your addItemToCart method "
                    + "failed to return the expected output "
                    + "when adding an item to a full cart.");
            System.out.print("The actual cart was: {");
            for (String items : cartFull) {
                System.out.print(items + " ");
            }
            System.out.println("}");
            System.out.println("The expected cart was {Milk, Apple, Banana, Pizza}");
            return false;
        }

        //3. AddItemToCart some items
        String[] cartHalfFull = new String[]{"Milk", "Apple", "Milk", null, null};
        size = 3;
        expected = 4;
        if (ShoppingCart.addItemToCart(inputItem1, cartHalfFull, size) != expected) {
            System.out.println("Problem detected: Your addItemToCart method "
                    + "failed to return the expected output "
                    + "when adding an item to a half-full cart.");
            System.out.print("The actual cart was: {");
            for (String items : cartFull) {
                System.out.print(items + " ");
            }
            System.out.println("}");
            System.out.println("The expected cart was {Milk, Apple, Milk, Banana, null");
            return false;
        }

        //4. Contains() a normal cart
        boolean expectedBool = true;
        //Banana was added in Test1 via inputItem1
        if (ShoppingCart.contains("Banana", cart, 1) != expectedBool) {
            System.out.println("Problem detected: Your contains() method "
                    + " failed to return the expected output" +
                    " when checking if Banana was in a cart.");
            return false;
        }
        //5. Contains() empty cart
        String[] emptyCart = new String[3];
        expectedBool = false;
        if (ShoppingCart.contains("Banana", emptyCart, 0) != expectedBool) {
            System.out.println("Problem detected: Your contains() method "
                    + " failed to return the expected output" +
                    " when checking if Banana was in an empty cart.");
            return false;
        }

        //6. nbOccurrences normal cart
        String[] cartHalfFullOccurences = new String[]{"Milk", "Apple", "Milk", null, null};
        size = 3;
        expected = 2;
        if (ShoppingCart.nbOccurrences("Milk", cartHalfFullOccurences, size) != expected) {
            System.out.println("Problem detected: Your nbOccurrences() method "
                    + "failed to return the expected output "
                    + "when counting how many milks were in the cart.");
            return false;
        }

        //7. nbOccurrences empty cart
        expected = 0;
        if (ShoppingCart.nbOccurrences("Milk", emptyCart, size) != expected) {
            System.out.println("Problem detected: Your nbOccurrences() method "
                    + "failed to return the expected output "
                    + "when counting how many milks were in the cart.");
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of the ShoppingCart.removeItem() method
     * @return true when this test verifies a correct functionality,
     * and false otherwise
     */
    public static boolean testRemoveItem() {

        // 1. Removing item from empty cart
        String[] emptyCart = new String[3];
        int size = 0;
        int expected = 0;
        if (ShoppingCart.removeItem(emptyCart, "Apple", size) != expected) {
            System.out.println("Problem detected: Your removeItem method "
                    + "failed to return the expected output "
                    + "when removing an item in an empty cart.");
            System.out.print("The actual cart was: {");
            for (String items : emptyCart) {
                System.out.print(items + " ");
            }
            System.out.println("}");
            System.out.println("The expected cart was {null, null, null}");
            return false;
        }
        //2. Not found item
        String[] normalCart = new String[]{"Milk", "Apple", "Banana", "Pizza", "Milk", null};
        size = 5;
        expected = 5;
        if (ShoppingCart.removeItem(normalCart, "Eggs", size) != expected) {
            System.out.println("Problem detected: Your removeItem method "
                    + "failed to return the expected output "
                    + "when removing an item not in the cart.");
            System.out.print("The actual cart was: {");
            for (String items : normalCart) {
                System.out.print(items + " ");
            }
            System.out.println("}");
            System.out.println("The expected cart was {Milk, Apple, Banana, Pizza, Milk, null}");
            return false;
        }
        //3.Successful Removal from arbitrary position
        String[] cart = new String[]{"Milk", "Apple", "Banana", "Apple", "Pizza", null, null};
        size = 5;
        expected = 4;
        if (ShoppingCart.removeItem(cart, "Apple", size) != expected) {
            System.out.println("Problem detected: Your removeItem method "
                    + "failed to return the expected output "
                    + "when removing an item found in the cart.");
            System.out.print("The actual cart was: {");
            for (String items : cart) {
                System.out.print(items + " ");
            }
            System.out.println("}");
            System.out.println("Expected cart was {Milk, Pizza, Banana, Apple, null, null, null}");
            return false;
        }
        //4. Successful Removal from index 0;
        String[] index0Cart = new String[]{"Milk", null, null};
        size = 1;
        expected = 0;
        if (ShoppingCart.removeItem(index0Cart, "Milk", size) != expected) {
            System.out.println("Problem detected: Your removeItem method "
                    + "failed to return the expected output "
                    + "when removing an item found in the first cart index.");
            System.out.print("The actual cart was: {");
            for (String items : index0Cart) {
                System.out.print(items + " ");
            }
            System.out.println("}");
            System.out.println("The expected cart was {null, null null}");
            return false;
        }
        //5. Successful Removal from last index
        String[] lastIndexCart = new String[]{"Eggs", "Apple", "Milk"};
        size = 3;
        expected = 2;
        if (ShoppingCart.removeItem(lastIndexCart, "Milk", size) != expected) {
            System.out.println("Problem detected: Your removeItem method "
                    + "failed to return the expected output "
                    + "when removing an item found in the last cart index.");
            System.out.print("The actual cart was: {");
            for (String items : lastIndexCart) {
                System.out.print(items + " ");
            }
            System.out.println("}");
            System.out.println("The expected cart was {Eggs, Apple, null}");
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of ShoppingCart.getCartSummary() & ShoppingCart.checkout() methods
     * @return true when this test verifies a correct functionality,
     * and false otherwise
     */
    public static boolean testCheckoutGetCartSummary() {
        //1. Empty Cart
        String[] emptyCart = new String[3];
        String expected = "";

        if (!ShoppingCart.getCartSummary(emptyCart, 0).equals(expected)) {
            System.out.println("Problem detected: Your getCartSummary method "
                    + "failed to return the expected output "
                    + "from an empty cart");
            System.out.println("It produced :" + "\n" +
                    ShoppingCart.getCartSummary(emptyCart, 0));
            System.out.println("Expected was : ");
            return false;
        }

        //2. Non Dupe Cart
        String[] nonDupeCart = new String[]{"Milk", "Banana", "Apple", null};
        expected = "(1) Milk\n" + "(1) Banana\n" + "(1) Apple\n";

        if (!ShoppingCart.getCartSummary(nonDupeCart, 3).equals(expected)) {
            System.out.println("Problem detected: Your getCartSummary method "
                    + "failed to return the expected output "
                    + "from a non duped cart");
            System.out.println("It produced :\n" +
                    ShoppingCart.getCartSummary(nonDupeCart, 3));
            System.out.println("Expected was :\n" + expected);
            return false;
        }
        //3. Dupe Cart
        String[] dupeCart = new String[]{"Tomato", "Milk", "Milk",
                "Eggs", "Tomato", "Onion", "Eggs", "Milk"};
        expected = "(2) Tomato\n" + "(3) Milk\n" + "(2) Eggs\n" + "(1) Onion\n";

        if (!ShoppingCart.getCartSummary(dupeCart, 8).equals(expected)) {
            System.out.println("Problem detected: Your getCartSummary method "
                    + "failed to return the expected output "
                    + "from a cart with dupes");
            System.out.println("It produced :\n" +
                    ShoppingCart.getCartSummary(dupeCart, 3));
            System.out.println("Expected was : \n" + expected);
            return false;
        }
        //4. empty cart checkout;
        double expectedTotal = 0.0;
        if (Math.abs(ShoppingCart.checkout(emptyCart, 0) - expectedTotal) > 0.001) {
            System.out.println("Problem detected: Your checkout method "
                    + "failed to return the expected output "
                    + "from an empty cart");
            return false;
        }
        //5. Non dupe cart
        expectedTotal = 4.37;
        if (Math.abs(ShoppingCart.checkout(nonDupeCart, 3) - expectedTotal) > 0.001) {
            System.out.println(ShoppingCart.checkout(nonDupeCart, 3));
            System.out.println("Problem detected: Your checkout method "
                    + "failed to return the expected output "
                    + "from a cart with no dupes");
            return false;
        }
        return true;
    }

    /**
     * Runs all the individual test methods
     * @return true when this test verifies correct functionality from all tests
     * and false otherwise
     */
    public static boolean runAllTests() {
        if (testGetProductPrice() == false) {
            System.out.println("testGetProductPrice returned false");
            return false;
        }
        if (testLookupMethods() == false) {
            System.out.println("testLookupMethods returned false");
            return false;
        }
        if (testAddItemToCartContainsNbOccurrences() == false) {
            System.out.println("testAddItemToCartContainsNbOccurrences returned false");
            return false;
        }
        if (testRemoveItem() == false) {
            System.out.println("testRemoveItem returned false");
            return false;
        }
        if (testCheckoutGetCartSummary() == false) {
            System.out.println("testCheckoutGetCartSummary returned false");
            return false;
        }
        return true;
    }

}

