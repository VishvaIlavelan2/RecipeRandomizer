// Creates an Ingredient data type that stores every unique individual ingredient
// and its respective weights. Provides methods to calculate ingredient probabilities
// and modify weights. Utilized by Recipe.java

public class Ingredient {
    private ST<String, Double> ingredientProbabilities; // stores ingredient probabilities
    private ST<String, Double> ingredientWeights;   // stores ingredient weights
    private final String stringINGREDIENT;
    // String constant to store format for printing ingredients

    public Ingredient() {

        ingredientProbabilities = new ST<>();
        ingredientWeights = new ST<>();
        stringINGREDIENT = "ingredient: ";

    }

    // returns a double and the increment factor
    private double incrementFactor() {
        return ingredientWeights.size() * 0.7;
    }

    // returns a double the sum of all weights
    private double totalWeights() {
        double counter = 0;
        for (String key : ingredientWeights) {
            counter += ingredientWeights.get(key);
        }
        return counter;
    }

    // inputs weights into ST ingredientWeights
    public void inputWeights(String ingredient, double weight) {
        ingredientWeights.put(ingredient, weight);

    }

    // takes in a String[] of ingredients
    // and calculates the weights of those ingredients
    public void calculateWeights(String[] ingredients) {
        double incrementFactor = incrementFactor();

        // takes stored increment factor and adds to respective weights

        for (int i = 0; i < ingredients.length; i++) {
            double weight = ingredientWeights.get(ingredients[i]) + incrementFactor;
            ingredientWeights.put(ingredients[i], weight);
        }

    }

    // calculates ingredient probabilities
    public void calculateProbabilities() {

        // iterates through weight St and calculate probabilities

        for (String key : ingredientWeights) {
            double probability = ingredientWeights.get(key) / totalWeights();
            ingredientProbabilities.put(key, probability);
        }
    }

    // returns a double[] array of ingredient probabilities
    // takes in String[] of ingredients
    public double[] returnProbs(String[] ingredients) {
        double[] probabilities = new double[ingredients.length];
        for (int i = 0; i < ingredients.length; i++) {
            probabilities[i] = ingredientProbabilities.get(ingredients[i]);
        }
        return probabilities;
    }

    // prints out ingredients and corresponding weights
    public void printWeights() {
        for (String key : ingredientWeights) {
            StdOut.println(stringINGREDIENT + key);
            StdOut.println("weight: " + ingredientWeights.get(key));

        }
    }

    // prints out ingredients and corresponding probabilities
    public void printProbs() {
        for (String key : ingredientProbabilities) {
            StdOut.println(stringINGREDIENT + key);
            StdOut.println("probs: " + ingredientProbabilities.get(key));

        }
    }

    public static void main(String[] args) {

    }
}
