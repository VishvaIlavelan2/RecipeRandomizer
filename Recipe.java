// This Recipe data type class performs the search and calculation algorithms for
// our search / I'm Lucky features. Stores the recipes and their associated
// values in symbol tables, and has methods to return values as needed
// for RecipeRandomizer client

public class Recipe {
    // creates a new ingredient data type
    private Ingredient ingredient;
    // creates an ST to store recipe and corresponding array of ingredients
    private ST<String, String[]> recipeTable;
    // creates an ST to store recipes and corresponding links
    private ST<String, String> linkTable;
    // creates an ST to store recipes for the Search feature
    private ST<Integer, String> recipeSearch;
    // creates String constant to store recipe printing format

    public Recipe() {
        recipeTable = new ST<>();
        linkTable = new ST<>();
        ingredient = new Ingredient();
        recipeSearch = new ST<>();

        // reads in Recipe Dataset
        while (!StdIn.isEmpty()) {

            // reads in amount of ingredients
            int ingredientnumber = StdIn.readInt();

            // reads but doesn't store space
            // so StdIn.readLine() can be used
            StdIn.readChar();

            // used source to help with reading a line
            // https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdIn.html
            String recipename = StdIn.readLine();

            String[] ingredientList = new String[ingredientnumber];

            // iterates through a particular recipe's ingredients
            // inputs into weights ST in ingredient datatype
            // all weights start out as 1

            for (int j = 0; j < ingredientnumber; j++) {
                String ingredientStdIn = StdIn.readString();
                ingredientList[j] = ingredientStdIn;
                ingredient.inputWeights(ingredientStdIn, 1);
            }

            String link = StdIn.readString();

            // inputs array of ingredients and respective recipe into
            // recipeTable

            recipeTable.put(recipename, ingredientList);

            // inputs recipe and its associated link into symbol table for
            // links
            linkTable.put(recipename, link);
        }
        ingredient.calculateProbabilities();
    }

    // calculates the ingredient average for a particular recipe
    // takes in a recipe name as a String parameter
    private double recipeAverage(String recipe) {
        String[] ingredients = recipeTable.get(recipe);
        int length = ingredients.length;
        double[] ingredientprobs = ingredient.returnProbs(ingredients);


        double sum = 0;

        // adds together all ingredient probabilities

        for (int i = 0; i < length; i++) {
            sum += ingredientprobs[i];
        }

        // calculates average

        double average = sum / length;
        return average;
    }

    // calculates the probabilities of the recipes
    // within recipeTable
    private double[] recipeProbabilities() {
        double sum = 0;
        double[] probability = new double[recipeTable.size()];
        for (String key : recipeTable) {
            double average = recipeAverage(key);

            // calculates the sum of all recipe averages

            sum += average;
        }
        int counter = 0;

        // divides recipe average by sum to find probability for each
        // recipe

        for (String key : recipeTable) {
            probability[counter] = recipeAverage(key) / sum;
            counter++;
        }
        return probability;
    }

    // returns the amount of recipes stored for Lucky feature
    public int recipeNumber() {
        return recipeTable.size();
    }

    // returns a String link based on
    // a recipe String parameter
    public String getLink(String recipe) {
        return linkTable.get(recipe);
    }

    // removes a recipe from recipeTable
    public void removeRecipe(String recipe) {
        // used source to determine if we could remove a key from ST
        // https://introcs.cs.princeton.edu/java/code/javadoc/ST.html
        recipeTable.remove(recipe);
    }

    // takes in a String recipe and returns the associated ingredients array
    public String[] returnIngredients(String recipe) {
        return recipeTable.get(recipe);
    }

    // takes in a String recipe and
    // modifies the ingredient probabilities
    public void modifyProbs(String recipe) {
        ingredient.calculateWeights(recipeTable.get(recipe));
        ingredient.calculateProbabilities();
    }

    // returns a String[] of recipes based on a searched ingredient
    // takes in String that gives the searched ingredient
    public String[] searchRandom(String matchedIngredient) {
        String[] recipes = matchRecipes(matchedIngredient);
        StdRandom.shuffle(recipes);
        return recipes;
    }


    public String randomRecipe() {
        // used this source to generate a random recipe using the probabilities of each recipe
        // https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdRandom.html#discrete-int:A-
        int n = StdRandom.discrete(recipeProbabilities());

        String[] recipes = toArray();
        String random = recipes[n];
        return random;

    }

    // returns a String array
    // converts recipeTable to an array format
    public String[] toArray() {
        String[] recipeArray = new String[recipeTable.size()];
        int counter = 0;
        for (String key : recipeTable) {
            recipeArray[counter] = key;
            counter++;
        }
        return recipeArray;
    }

    // returns a double probability for a specific recipe
    public double returnRecipeProb(String recipe) {
        double[] probs = recipeProbabilities();
        String[] recipes = toArray();
        for (int i = 0; i < recipes.length; i++) {
            if (recipes[i].equals(recipe)) {
                return probs[i];
            }
        }
        return 0;
    }

    // returns a String[] of all the recipes that contain
    // a certain ingredient
    // takes a String in as a parameter
    public String[] matchRecipes(String matchIngredient) {

        String[] ingredientsArray;
        int counter = 0;

        // iterates through and stores all the recipes that have a certain
        // ingredient in a symbol table

        for (String key : recipeTable) {
            ingredientsArray = recipeTable.get(key);
            for (int i = 0; i < ingredientsArray.length; i++) {
                if (matchIngredient.equals(ingredientsArray[i])) {

                    // when recalled replaces / rewrites symbol table
                    // for different ingredients

                    recipeSearch.put(counter, key);
                    counter++;
                }
            }
        }

        // returns an empty array if no matches are found

        if (counter == 0) {
            String[] returnArray = new String[0];
            return returnArray;
        }

        // puts the symbol table into array format.
        // to avoid transfer of leftover recipes from other ingredients

        String[] matchedRecipes = new String[counter];
        for (int i = 0; i < matchedRecipes.length; i++) {
            matchedRecipes[i] = recipeSearch.get(i);
        }
        return matchedRecipes;

    }


    public static void main(String[] args) {

    }
}
