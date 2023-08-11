public class TestingClass {
    private static final String PASSED_TEST = "Has Test Passed: ";
    private static final String SEARCH_EGG = "egg";

    public TestingClass() {
    }


    public static void testFeature2() {
        Recipe recipe = new Recipe();

        // tests whether all recipes have equal probability following initialization

        boolean test1 = false;
        String[] recipes = recipe.toArray();
        double[] probs = new double[recipes.length];
        for (int i = 0; i < recipes.length; i++) {
            probs[i] = recipe.returnRecipeProb(recipes[i]);
        }
        double counter = 0;
        for (int i = 0; i < probs.length; i++) {
            counter += probs[i];
        }

        // checks if all probabilities are the same

        if ((counter / probs.length) == probs[0]) {

            // in order for test to be true, all probabilites must be equal

            test1 = true;
        }

        StdOut.println("Test 1: All Recipe Probabilities After "
                               + "Initialiazation Are Equal");
        StdOut.println(PASSED_TEST + test1);

        StdOut.println("---------------------------------------------------------");

        String testRecipe = "Quick Barbecue Wings";
        boolean test2 = false;
        recipe.modifyProbs(testRecipe);

        // double prob is the expected probability when "Quick Barbecue Wings"
        // is liked

        // this value is found through by calculating ingredient weights
        // increase by (70% for associated ingredients)

        // ingredient probabilities are calculated using weights
        // then averaged and normalized to caluclate recipe probabilities

        double prob = 0.8113207547169812;
        double testprob = recipe.returnRecipeProb(testRecipe);
        if (testprob == prob) {

            // returns true if the calculated probability is equal to
            // the expected recipe probability

            test2 = true;
        }
        StdOut.println("Test 2: Check Recipe Probability After Modification");
        StdOut.println(PASSED_TEST + test2);
    }


    public static void testFeature3() {
        Recipe recipe = new Recipe();
        boolean test1 = true;

        // checks if the matchRecipes() method returns an array of the correct
        // recipes for a certain ingredient, in alphabetical order

        String[] correctRecipes = { "Crab Cakes", "Potato and Cheese Pie" };
        String[] recipeArray = recipe.matchRecipes(SEARCH_EGG);
        for (int i = 0; i < recipeArray.length; i++) {

            // all elements of both arrays must be equal to each
            // other for tests to pass

            if (!correctRecipes[i].equals(recipeArray[i])) {
                test1 = false;
            }

        }

        // tests whether a recipe array is randomized correctly while maintaining
        // the correct recipes

        boolean test2 = false;
        String[] randomizedRecipes = recipe.searchRandom(SEARCH_EGG);
        int counter = 0;
        for (int i = 0; i < randomizedRecipes.length; i++) {

            // counts the amount of matches present from shuffling recipe array

            if (randomizedRecipes[i].equals(recipeArray[i])) {
                counter++;
            }
        }

        // test passes if the randomization array passes test1, indicating
        // same recipes in array

        // and if the amount of recipe matches with an ordered array is less
        // than array length

        if (test1 && counter != correctRecipes.length) {
            test2 = true;
        }
        StdOut.println("Test 1: Does matchRecipes method Return the Correct "
                               + "Recipes for a Given Ingredient?");
        StdOut.println(PASSED_TEST + test1);
        StdOut.println("---------------------------------------------------------");

        StdOut.println("Test 2: Does The Search Feature Return"
                               + " A Random Recipe Array Order?");
        StdOut.println(PASSED_TEST + test2);


    }

    public static void main(String[] args) {

        // type in feature2 or feature3 to test a particular feature
        // first feature is GUI so refer to comments in RecipeRandomizer

        // use TestingDataset.txt as input

        String testFeature = args[0];
        if (testFeature.equals("feature2")) {
            testFeature2();
        }
        if (testFeature.equals("feature3")) {
            testFeature3();
        }
    }
}
