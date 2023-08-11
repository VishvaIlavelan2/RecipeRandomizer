import java.awt.Color;
import java.awt.Font;

// RecipeRandomizer class acts as a client that utilizes the Recipe and Ingredient
// data types to simulate the RecipeRandomizer application
// Has methods to set up, clear, and simulate different GUI states.

public class RecipeRandomizer {
    private static final Font FONT1 = new Font("Georgia", Font.BOLD, 36); // large title font
    private static final Font FONT2 = new Font("Georgia", Font.BOLD, 24); // smaller subtitle font
    private static final Font FONT3 = new Font("Arial", Font.BOLD, 16); // smallest text font
    private static final String DISLIKE = "Dislike"; // dislike button label
    private static final String LIKE = "Like"; // like button label
    private static final String NO_RECIPES = "No More Recipes!"; // mo more recipes left
    private static final String SEARCH_STRING = "Search for Ingredient";
    // Search for ingredient button label
    private static final String FEELING_LUCKY = "I'm Feeling Lucky";
    // I'm feeling lucky button label
    private static final String LUCKY = "Lucky"; // lucky string
    private static final String SEARCH = "Search"; // search string

    // constructor
    public RecipeRandomizer() {

    }

    // user interface for home screen
    public static String homeScreen() {
        StdDraw.clear();

        int maxNumber = 500;

        // sets scale
        StdDraw.setXscale(0, maxNumber);
        StdDraw.setYscale(0, maxNumber);

        // draws two buttons on the home screen
        StdDraw.setPenColor(Color.lightGray);
        int rectangleWidth = 90;
        int rectangleHeight = 70;
        StdDraw.filledRectangle(150, 100, rectangleWidth, rectangleHeight);
        StdDraw.filledRectangle(350, 100, rectangleWidth, rectangleHeight);
        StdDraw.setPenColor(Color.black);

        // labels buttons
        StdDraw.setFont(FONT3);
        StdDraw.text(150, 100, SEARCH_STRING);
        StdDraw.text(350, 100, FEELING_LUCKY);

        // sets title
        StdDraw.setFont(FONT1);
        StdDraw.text(250, 400, "Recipe Randomizer");

        // sets instructions
        StdDraw.setFont(FONT2);
        StdDraw.text(250, 300, "Click a Button to Begin");

        // loop that checks which button is clicked on home screen
        while (true) {
            int button = checkMouseInputHomeScreen();
            String buttonName;

            // if first button is clicked, search string is returned
            if (button == 1) {
                buttonName = SEARCH;
                StdDraw.clear();
                StdOut.println(buttonName);
                return buttonName;
            }

            // if second button is clicked, lucky string is returned
            if (button == 2) {
                buttonName = LUCKY;
                StdDraw.clear();
                StdOut.println(buttonName);
                return buttonName;
            }

        }
    }


    // checks which button the mouse clicks
    public static int checkMouseInputHomeScreen() {
        // checks mouse location
        // used source to determine how to find mouse coordinates
        // https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html#mouseX–
        double mouseX = StdDraw.mouseX();
        double mouseY = StdDraw.mouseY();

        // returns true if mouse's location is inside button one and button two
        boolean buttonOne = mouseX >= 60 && mouseX <= 240 && mouseY <= 170 && mouseY >= 30;
        boolean buttonTwo = mouseX >= 260 && mouseX <= 440 && mouseY >= 30 && mouseY <= 170;

        // checks if the mouse is pressed
        if (!StdDraw.isMousePressed()) {
            return 0;
        }

        // checks if the mouse clicked in the first button
        if (buttonOne) {
            return 1; // means first button
        }

        // checks if the mouse clicked in the second button
        if (buttonTwo) {
            return 2; // means second button
        }

        // otherwise return 0
        return 0;
    }

    // checks if user typed any keys during the 'search' feature
    public static char checkKeysSearch() {
        if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            return key;
        }
        // returns empty char if no keys typed
        return ' ';
    }

    // checks if user pressed enter
    public static boolean checkEnter() {
        // used source to determine values for a key event
        // https://docs.oracle.com/javase/8/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_F7
        int enter = 10;

        return StdDraw.isKeyPressed(enter);
    }

    // checks if user typed any keys during the 'I'm feeling lucky' feature
    public static String checkKeysLucky() {
        while (StdDraw.hasNextKeyTyped()) {
            String returnString;
            char key = StdDraw.nextKeyTyped();

            // key Y means like a recipe
            if (key == 'y') {
                returnString = LIKE;
                return returnString;
            }

            // key N means dislike a recipe
            if (key == 'n') {
                returnString = DISLIKE;
                return returnString;
            }

            // used source to determine values for a key event
            // https://docs.oracle.com/javase/8/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_F7
            int esc = 27;

            // key Esc means exit to home screen
            if (StdDraw.isKeyPressed(esc)) {
                returnString = "exit";
                return returnString;
            }
        }
        // returns empty string if no keys typed
        return "";
    }

    // changes button colors depending on button pressed during the 'I'm feeling lucky' feature
    public static void changeColor(Color color) {
        // checks color, either red or green
        StdDraw.setPenColor(color);

        // if red color called, dislike button is pressed
        if (color == Color.RED) {
            StdDraw.filledRectangle(150, 70, 80, 35);
            StdDraw.setPenColor(Color.black);
            StdDraw.text(150, 70, DISLIKE);
            StdDraw.pause(60);
            StdDraw.setPenColor(Color.gray);
            StdDraw.filledRectangle(150, 70, 80, 35);
            StdDraw.setPenColor(Color.black);
            StdDraw.text(150, 70, DISLIKE);
        }

        // if green color called, like button is pressed
        if (color == Color.GREEN) {
            StdDraw.filledRectangle(350, 70, 80, 35);
            StdDraw.setPenColor(Color.black);
            StdDraw.text(350, 70, LIKE);
            StdDraw.pause(60);
            StdDraw.setPenColor(Color.gray);
            StdDraw.filledRectangle(350, 70, 80, 35);
            StdDraw.setPenColor(Color.black);
            StdDraw.text(350, 70, LIKE);
        }

    }


    // sets up user interface for the 'I'm feeling lucky' feature
    public static void luckyUI() {
        // sets title
        StdDraw.setFont(FONT1);
        StdDraw.text(250, 420, FEELING_LUCKY);

        // sets ingredient label
        StdDraw.setFont(FONT3);
        StdDraw.text(100, 300, "Ingredients:");

        // draws two buttons, like and dislike and provides instructions
        StdDraw.setPenColor(Color.lightGray);
        StdDraw.filledRectangle(150, 70, 80, 35);
        StdDraw.filledRectangle(350, 70, 80, 35);

        // labels the buttons
        StdDraw.setFont(FONT3);
        StdDraw.setPenColor(Color.black);
        StdDraw.text(150, 70, DISLIKE);
        StdDraw.text(350, 70, LIKE);

        // writes instructions
        StdDraw.text(150, 20, "Press Key N for Dislike");
        StdDraw.text(350, 20, "Press Key Y for Like");
        StdDraw.text(100, 480, "Press Key Esc for Home");
    }

    // resets screen for the 'I'm feeling lucky' feature user interface
    public static void resetLucky() {
        StdDraw.clear();
        luckyUI();
    }

    // prints ingredients in a recipe to user interface
    public static void printIngredients(Recipe recipe, String recipeName, int yValue) {
        StdDraw.setPenColor(Color.blue);

        // retrieves the ingredients within a recipe
        String[] ingredients = recipe.returnIngredients(recipeName);

        StringBuilder stringBuilder = new StringBuilder();
        int y = yValue; // y coordinate of text
        int x = 250; // x coordinate of text

        // if there are 4 or fewer ingredients, draw the text on screen in a line
        int length = ingredients.length;
        if (length < 4) {
            for (int i = 0; i < length; i++) {
                stringBuilder.append(ingredients[i]);
                stringBuilder.append(" ");
            }
            StdDraw.text(x, y, stringBuilder.toString());
        }

        // if there are more than 4 ingredients, create a new line of ingredients shifted down
        // and do this every 4 ingredients
        else {
            for (int j = 0; j < length; j++) {
                stringBuilder.append(ingredients[j]);
                stringBuilder.append(" ");
                if (j % 4 == 3) {
                    StdDraw.text(x, y, stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                    y -= 20;
                }
            }
            StdDraw.text(x, y, stringBuilder.toString());
        }
        StdDraw.setPenColor(Color.black);
    }

    // simulates the lucky feature
    public static void simulateLucky(Recipe recipe) {
        luckyUI();
        StdDraw.setPenColor(Color.blue);
        String recipeName;
        // if there are no more recipes, write that on the screen
        if (recipe.recipeNumber() == 0) {
            StdDraw.text(250, 350, NO_RECIPES);
        }
        else {
            // find a random recipe to start with
            recipeName = recipe.randomRecipe();
            StdDraw.text(250, 350, recipeName);
            printIngredients(recipe, recipeName, 240);

            // check user input to see like or dislike
            while (true) {
                String userInput = checkKeysLucky();

                // response if user clicks like
                if (userInput.equals(LIKE)) {
                    resetLucky();
                    StdDraw.setPenColor(Color.blue);

                    // if there are no more recipes
                    if (recipe.recipeNumber() == 1 || recipe.recipeNumber() == 0) {
                        StdDraw.text(250, 350, NO_RECIPES);
                        recipe.removeRecipe(recipeName);
                    }

                    // modify ingredients probabilities and output a random recipe
                    // taking into account weight of ingredients
                    else {
                        recipe.modifyProbs(recipeName);
                        recipe.removeRecipe(recipeName);
                        recipeName = recipe.randomRecipe();
                        StdDraw.text(250, 350, recipeName);
                        printIngredients(recipe, recipeName, 240);
                        StdOut.println(recipe.getLink(recipeName));
                        changeColor(Color.GREEN);
                    }
                }

                // response if user clicks dislike
                if (userInput.equals(DISLIKE)) {
                    resetLucky();
                    StdDraw.setPenColor(Color.blue);
                    recipe.removeRecipe(recipeName);

                    // if there are no more recipes
                    if (recipe.recipeNumber() == 0) {
                        StdDraw.text(250, 350, NO_RECIPES);
                    }

                    // don't modify probabilities, but still output a random
                    // recipe taking into account weight of ingredients
                    else {
                        recipeName = recipe.randomRecipe();
                        StdDraw.text(250, 350, recipeName);
                        printIngredients(recipe, recipeName, 240);
                        StdOut.println(recipe.getLink(recipeName));
                        changeColor(Color.RED);
                    }
                }

                // check if user pressed escape and return to home screen
                if (userInput.equals("exit")) {
                    StdDraw.clear();
                    String button = homeScreen();
                    if (button.equals(LUCKY)) {
                        simulateLucky(recipe);
                    }
                    if (button.equals(SEARCH)) {
                        simulateSearch(recipe);
                    }

                }
                StdDraw.setPenColor(Color.black);
            }
        }
    }


    // sets up user interface for the 'search for ingredient' feature
    public static void searchUI() {

        // sets title
        StdDraw.setFont(FONT1);
        StdDraw.text(250, 420, SEARCH_STRING);

        // sets up instructions
        StdDraw.setFont(FONT3);
        StdDraw.text(100, 480, "Press Key Esc for Home");
        StdDraw.text(195, 460, "Press Comma for Next Recipe / Press Tab to Reset");
        StdDraw.text(250, 375, "Instructions: Type an ingredient and press enter");
        StdDraw.text(250, 350,
                     "Use singular ingredients and dashes for spaces");
        StdDraw.text(250, 125, SEARCH + ":");

        // creates text box
        double penRadius = StdDraw.getPenRadius();
        StdDraw.setPenRadius(3.0 * penRadius);
        StdDraw.rectangle(250, 75, 175, 40);
        StdDraw.setPenRadius(penRadius);

        // sets ingredient label
        StdDraw.setFont(FONT3);
        StdDraw.text(80, 260, "Ingredients:");

    }

    // resets to a blank outline of 'search for ingredient' UI
    public static void resetSearch() {
        StdDraw.clear();
        searchUI();
    }

    // takes into account all possibilities after user presses enter to search for an ingredient
    public static void ifEnter(Recipe recipe, String string) {
        resetSearch();

        // creates a shuffled array of recipes that all contain the desired ingredient
        String[] recipeArray = recipe.searchRandom(string);

        // lets the user know if there were no matches to the ingredient they inputted
        if (recipeArray.length == 0) {
            StdDraw.text(255, 300, "No Recipe Available");
            return;
        }

        // outputs the first recipe in the array
        StdDraw.setPenColor(Color.blue);
        StdDraw.text(255, 300, recipeArray[0]);
        printIngredients(recipe, recipeArray[0], 235);
        StdDraw.setPenColor(Color.black);
        StdOut.println(recipe.getLink(recipeArray[0]));
        int counter = 1;

        // checks if the user has pressed the tab button to refresh the screen
        if (checkTab()) {
            resetSearch();
            simulateSearch(recipe);
        }

        // loops through the array of match recipes when the user presses the comma button
        while (true) {
            char key2 = checkKeysSearch();

            // checks if the user presses the escape button
            ifEscape(recipe);

            // checks if the user presses the tab button
            if (checkTab()) {
                resetSearch();
                simulateSearch(recipe);
            }

            // checks if the user presses the comma button
            if (key2 == ',') {
                // if there are no more matched recipes left to see, let the user know
                if (recipeArray.length == counter) {
                    resetSearch();
                    StdDraw.setPenColor(Color.blue);
                    StdDraw.text(255, 300, "No Recipes Left");
                    StdDraw.setPenColor(Color.black);
                    break;
                }

                // output the next recipe in the array
                resetSearch();
                StdDraw.setPenColor(Color.blue);
                StdDraw.text(255, 300, recipeArray[counter]);
                printIngredients(recipe, recipeArray[counter], 235);
                StdDraw.setPenColor(Color.black);
                StdOut.println(recipe.getLink(recipeArray[counter]));
                counter++;
            }
        }

        // check if the user presses the tab button
        if (checkTab()) {
            resetSearch();
            simulateSearch(recipe);
        }

    }

    // directs the user to the home screen if they press the escape button
    public static void ifEscape(Recipe recipe) {
        // used source to determine values for a key event
        // https://docs.oracle.com/javase/8/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_F7
        int esc = 27;

        if (StdDraw.isKeyPressed(esc)) {
            String button = homeScreen();
            if (button.equals(SEARCH)) {
                simulateSearch(recipe);
            }
            if (button.equals((LUCKY))) {
                simulateLucky(recipe);
            }
        }
    }

    // simulates the search feature
    public static void simulateSearch(Recipe recipe) {
        searchUI();
        String string = "";

        // loop that checks the keys the user clicks and responds accordingly
        while (true) {

            char key = checkKeysSearch();

            // starts the loop over if no key is clicked
            if (key == ' ') {
                continue;
            }

            // checks if the user presses the escape button
            ifEscape(recipe);


            // checks if the user presses the enter button
            if (checkEnter()) {
                ifEnter(recipe, string);
                string = "";
            }

            // checks if the user presses the tab button
            if (checkTab()) {
                resetSearch();
                simulateSearch(recipe);
            }

            // used source to determine values for a key event
            // https://docs.oracle.com/javase/8/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_F7
            int backspace = 8;

            // removes a character from the string if the user presses the backspace button
            if (StdDraw.isKeyPressed(backspace) && string.length() != 0) {
                // used source to determine how to make a substring
                // https://docs.oracle.com/javase/6/docs/api/java/lang/String.html
                string = string.substring(0, string.length() - 1);
                resetSearch();
                StdDraw.setFont(FONT2);
                StdDraw.text(255, 75, string);
            }

            // if the user types a key which is a letter, the key is concatenated
            // to their search for an ingredient
            else {
                // used source to find ASCII values for keys
                // https://www.cs.princeton.edu/courses/archive/spring09/cos217/precepthandouts/03/ascii.pdf
                if (key > 96 && key < 126) {
                    string += String.valueOf(key);
                    resetSearch();
                    StdDraw.setFont(FONT2);
                    StdDraw.text(255, 75, string);
                }
            }

        }
    }

    // resets the search user interface if the user presses the tab button
    public static boolean checkTab() {
        // used source to determine values for a key event
        // https://docs.oracle.com/javase/8/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_F7
        int tab = 9;

        if (StdDraw.isKeyPressed(tab)) {
            resetSearch();
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Recipe recipe = new Recipe();
        String button = homeScreen();
        if (button.equals(LUCKY)) {
            simulateLucky(recipe);
        }
        if (button.equals(SEARCH)) {
            simulateSearch(recipe);
        }


    }


}

// GUI Testing
// Test 1: Does state of the application change as expected when buttons
// I'm lucky and search are clicked?

// Input: Click squares that resemble buttons

// Output: Changes state accordingly to a different panel

// Result: Passed (tested manually)


// Test 2: Can you return to the home screen?

// Input: esc key

// Output: returns to home screen

// Result: Passed (tested manually)

