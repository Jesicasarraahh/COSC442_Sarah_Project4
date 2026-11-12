package edu.towson.cis.cosc442.project4.coffeemaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CoffeeMakerTest {

    private CoffeeMaker maker;

    @BeforeEach
    void setUp() {
        maker = new CoffeeMaker();
    }

    // Used to create recipes for the tests.
    private Recipe createRecipe(String name, int price) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setPrice(price);
        recipe.setAmtCoffee(2);
        recipe.setAmtMilk(3);
        recipe.setAmtSugar(1);
        recipe.setAmtChocolate(1);
        return recipe;
    }

    @Test
    void testStartingInventory() {
        // Arrange: setUp creates the coffee maker

        // Act
        Inventory inventory = maker.checkInventory();

        // Assert
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    @Test
    void testAddRecipe() {
        // Arrange
        Recipe recipe = createRecipe("Latte", 50);

        // Act
        boolean added = maker.addRecipe(recipe);
        Recipe stored = maker.getRecipeForName("Latte");

        // Assert
        assertTrue(added);
        assertEquals("Latte", stored.getName());
        assertEquals(50, stored.getPrice());
        assertEquals(2, stored.getAmtCoffee());
        assertEquals(3, stored.getAmtMilk());
        assertEquals(1, stored.getAmtSugar());
        assertEquals(1, stored.getAmtChocolate());
    }

    @Test
    void testDuplicateRecipe() {
        // Arrange
        maker.addRecipe(createRecipe("Latte", 50));
        Recipe duplicate = createRecipe("Latte", 75);

        // Act
        boolean added = maker.addRecipe(duplicate);

        // Assert
        assertFalse(added);
        assertEquals(50, maker.getRecipeForName("Latte").getPrice());
    }

    @Test
    void testThreeRecipes() {
        // Arrange
        Recipe first = createRecipe("Latte", 50);
        Recipe second = createRecipe("Mocha", 60);
        Recipe third = createRecipe("Coffee", 40);

        // Act
        boolean addedFirst = maker.addRecipe(first);
        boolean addedSecond = maker.addRecipe(second);
        boolean addedThird = maker.addRecipe(third);

        // Assert
        assertTrue(addedFirst);
        assertTrue(addedSecond);
        assertTrue(addedThird);
        assertEquals("Latte", maker.getRecipeForName("Latte").getName());
        assertEquals("Mocha", maker.getRecipeForName("Mocha").getName());
        assertEquals("Coffee", maker.getRecipeForName("Coffee").getName());
    }

    @Test
    void testRecipeLimit() {
        // Arrange
        maker.addRecipe(createRecipe("Latte", 50));
        maker.addRecipe(createRecipe("Mocha", 60));
        maker.addRecipe(createRecipe("Coffee", 40));

        // Act
        boolean added = maker.addRecipe(createRecipe("Tea", 30));

        // Assert
        assertFalse(added);
        assertNull(maker.getRecipeForName("Tea").getName());
    }

    @Test
    void testGetRecipes() {
        // Arrange
        maker.addRecipe(createRecipe("Latte", 50));

        // Act
        Recipe[] recipes = maker.getRecipes();
        int count = 0;
        String name = null;

        for (Recipe recipe : recipes) {
            if (recipe != null && recipe.getName() != null) {
                count++;
                name = recipe.getName();
            }
        }

        // Assert
        assertEquals(1, count);
        assertEquals("Latte", name);
    }

    @Test
    void testMissingRecipe() {
        // Arrange
        maker.addRecipe(createRecipe("Latte", 50));

        // Act
        Recipe recipe = maker.getRecipeForName("Mocha");

        // Assert
        assertNotNull(recipe);
        assertNull(recipe.getName());
    }

    @Test
    void testDeleteRecipe() {
        // Arrange
        Recipe recipe = createRecipe("Latte", 50);
        maker.addRecipe(recipe);

        // Act
        boolean deleted = maker.deleteRecipe(recipe);

        // Assert
        assertTrue(deleted);
        assertNull(maker.getRecipeForName("Latte").getName());
    }

    @Test
    void testDeleteMissingRecipe() {
        // Arrange
        maker.addRecipe(createRecipe("Latte", 50));
        Recipe missing = createRecipe("Mocha", 60);

        // Act
        boolean deleted = maker.deleteRecipe(missing);

        // Assert
        assertFalse(deleted);
        assertEquals("Latte", maker.getRecipeForName("Latte").getName());
    }

    @Test
    void testDeleteTwice() {
        // Arrange
        Recipe recipe = createRecipe("Latte", 50);
        maker.addRecipe(recipe);
        maker.deleteRecipe(recipe);

        // Act
        boolean deleted = maker.deleteRecipe(recipe);

        // Assert
        assertFalse(deleted);
    }

    @Test
    void testAddAfterDelete() {
        // Arrange
        Recipe latte = createRecipe("Latte", 50);
        maker.addRecipe(latte);
        maker.addRecipe(createRecipe("Mocha", 60));
        maker.addRecipe(createRecipe("Coffee", 40));
        maker.deleteRecipe(latte);

        // Act
        boolean added = maker.addRecipe(createRecipe("Tea", 30));

        // Assert
        assertTrue(added);
        assertNull(maker.getRecipeForName("Latte").getName());
        assertEquals("Tea", maker.getRecipeForName("Tea").getName());
        assertEquals("Mocha", maker.getRecipeForName("Mocha").getName());
        assertEquals("Coffee", maker.getRecipeForName("Coffee").getName());
    }

    @Test
    void testEditRecipe() {
        // Arrange
        Recipe original = createRecipe("Latte", 50);
        maker.addRecipe(original);

        Recipe updated = createRecipe("Latte", 75);
        updated.setAmtCoffee(4);
        updated.setAmtMilk(5);
        updated.setAmtSugar(2);
        updated.setAmtChocolate(3);

        // Act
        boolean edited = maker.editRecipe(original, updated);
        Recipe stored = maker.getRecipeForName("Latte");

        // Assert
        assertTrue(edited);
        assertEquals("Latte", stored.getName());
        assertEquals(75, stored.getPrice());
        assertEquals(4, stored.getAmtCoffee());
        assertEquals(5, stored.getAmtMilk());
        assertEquals(2, stored.getAmtSugar());
        assertEquals(3, stored.getAmtChocolate());
    }

    @Test
    void testEditMissingRecipe() {
        // Arrange
        maker.addRecipe(createRecipe("Latte", 50));
        Recipe missing = createRecipe("Mocha", 60);
        Recipe updated = createRecipe("Latte", 90);

        // Act
        boolean edited = maker.editRecipe(missing, updated);

        // Assert
        assertFalse(edited);
        assertEquals(50, maker.getRecipeForName("Latte").getPrice());
    }

    @Test
    void testEditKeepsName() {
        // Arrange
        Recipe original = createRecipe("Latte", 50);
        maker.addRecipe(original);
        Recipe updated = createRecipe("Mocha", 75);

        // Act
        maker.editRecipe(original, updated);

        // Assert
        assertEquals("Latte", maker.getRecipeForName("Latte").getName());
        assertNull(maker.getRecipeForName("Mocha").getName());
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 0, 0",
        "1, 0, 0, 0",
        "0, 1, 0, 0",
        "0, 0, 1, 0",
        "0, 0, 0, 1",
        "2, 3, 4, 5"
    })
    void testAddInventory(int coffee, int milk, int sugar, int chocolate) {
        // Arrange
        Inventory inventory = maker.checkInventory();

        // Act
        boolean added = maker.addInventory(coffee, milk, sugar, chocolate);

        // Assert
        assertTrue(added);
        assertEquals(15 + coffee, inventory.getCoffee());
        assertEquals(15 + milk, inventory.getMilk());
        assertEquals(15 + sugar, inventory.getSugar());
        assertEquals(15 + chocolate, inventory.getChocolate());
    }

    @ParameterizedTest
    @CsvSource({
        "-1, 0, 0, 0",
        "0, -1, 0, 0",
        "0, 0, -1, 0",
        "0, 0, 0, -1",
        "2, 3, -1, 4"
    })
    void testInvalidInventory(int coffee, int milk, int sugar, int chocolate) {
        // Arrange
        Inventory inventory = maker.checkInventory();

        // Act
        boolean added = maker.addInventory(coffee, milk, sugar, chocolate);

        // Assert
        assertFalse(added);
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    @ParameterizedTest
    @CsvSource({
        "50, 0",
        "51, 1",
        "75, 25"
    })
    void testPurchase(int paid, int expectedChange) {
        // Arrange
        Recipe recipe = createRecipe("Latte", 50);
        maker.addRecipe(recipe);
        Inventory inventory = maker.checkInventory();

        // Act
        int change = maker.makeCoffee(recipe, paid);

        // Assert
        assertEquals(expectedChange, change);
        assertEquals(13, inventory.getCoffee());
        assertEquals(12, inventory.getMilk());
        assertEquals(14, inventory.getSugar());
        assertEquals(14, inventory.getChocolate());
    }

    @ParameterizedTest
    @CsvSource({
        "0",
        "49"
    })
    void testLowPayment(int paid) {
        // Arrange
        Recipe recipe = createRecipe("Latte", 50);
        maker.addRecipe(recipe);
        Inventory inventory = maker.checkInventory();

        // Act
        int change = maker.makeCoffee(recipe, paid);

        // Assert
        assertEquals(paid, change);
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 15, 15, 15",
        "15, 2, 15, 15",
        "15, 15, 0, 15",
        "15, 15, 15, 0"
    })
    void testLowInventory(int coffee, int milk, int sugar, int chocolate) {
        // Arrange
        Recipe recipe = createRecipe("Latte", 50);
        maker.addRecipe(recipe);

        Inventory inventory = maker.checkInventory();
        inventory.setCoffee(coffee);
        inventory.setMilk(milk);
        inventory.setSugar(sugar);
        inventory.setChocolate(chocolate);

        // Act
        int change = maker.makeCoffee(recipe, 75);

        // Assert
        assertEquals(75, change);
        assertEquals(coffee, inventory.getCoffee());
        assertEquals(milk, inventory.getMilk());
        assertEquals(sugar, inventory.getSugar());
        assertEquals(chocolate, inventory.getChocolate());
    }

    @Test
    void testExactIngredients() {
        // Arrange
        Recipe recipe = createRecipe("Latte", 50);
        maker.addRecipe(recipe);

        Inventory inventory = maker.checkInventory();
        inventory.setCoffee(2);
        inventory.setMilk(3);
        inventory.setSugar(1);
        inventory.setChocolate(1);

        // Act
        int change = maker.makeCoffee(recipe, 50);

        // Assert
        assertEquals(0, change);
        assertEquals(0, inventory.getCoffee());
        assertEquals(0, inventory.getMilk());
        assertEquals(0, inventory.getSugar());
        assertEquals(0, inventory.getChocolate());
    }
}