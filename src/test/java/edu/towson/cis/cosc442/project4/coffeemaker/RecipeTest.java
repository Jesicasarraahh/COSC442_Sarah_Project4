package edu.towson.cis.cosc442.project4.coffeemaker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    @ParameterizedTest
    @CsvSource({
        "-10, 0",
        "-1, 0",
        "0, 0",
        "1, 1",
        "50, 50",
        "2147483647, 2147483647"
    })
    void testPrice(int input, int expected) {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setPrice(25);

        // Act
        recipe.setPrice(input);

        // Assert
        assertEquals(expected, recipe.getPrice());
    }

    @ParameterizedTest
    @CsvSource({
        "-10, 0",
        "-1, 0",
        "0, 0",
        "1, 1",
        "5, 5",
        "2147483647, 2147483647"
    })
    void testCoffeeAmount(int input, int expected) {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setAmtCoffee(10);

        // Act
        recipe.setAmtCoffee(input);

        // Assert
        assertEquals(expected, recipe.getAmtCoffee());
    }

    @ParameterizedTest
    @CsvSource({
        "-10, 0",
        "-1, 0",
        "0, 0",
        "1, 1",
        "5, 5",
        "2147483647, 2147483647"
    })
    void testMilkAmount(int input, int expected) {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setAmtMilk(10);

        // Act
        recipe.setAmtMilk(input);

        // Assert
        assertEquals(expected, recipe.getAmtMilk());
    }

    @ParameterizedTest
    @CsvSource({
        "-10, 0",
        "-1, 0",
        "0, 0",
        "1, 1",
        "5, 5",
        "2147483647, 2147483647"
    })
    void testSugarAmount(int input, int expected) {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setAmtSugar(10);

        // Act
        recipe.setAmtSugar(input);

        // Assert
        assertEquals(expected, recipe.getAmtSugar());
    }

    @ParameterizedTest
    @CsvSource({
        "-10, 0",
        "-1, 0",
        "0, 0",
        "1, 1",
        "5, 5",
        "2147483647, 2147483647"
    })
    void testChocolateAmount(int input, int expected) {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setAmtChocolate(10);

        // Act
        recipe.setAmtChocolate(input);

        // Assert
        assertEquals(expected, recipe.getAmtChocolate());
    }

    @Test
    void testSetName() {
        // Arrange
        Recipe recipe = new Recipe();

        // Act
        recipe.setName("Latte");

        // Assert
        assertEquals("Latte", recipe.getName());
    }

    @Test
    void testChangeName() {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setName("Latte");

        // Act
        recipe.setName("Mocha");

        // Assert
        assertEquals("Mocha", recipe.getName());
    }

    @Test
    void testSameNameDifferentPrices() {
        // Arrange
        Recipe first = new Recipe();
        first.setName(new String("Latte"));
        first.setPrice(50);

        Recipe second = new Recipe();
        second.setName(new String("Latte"));
        second.setPrice(75);

        // Act
        boolean result = first.equals(second);

        // Assert
        assertTrue(result);
    }

    @Test
    void testDifferentNames() {
        // Arrange
        Recipe first = new Recipe();
        first.setName("Latte");

        Recipe second = new Recipe();
        second.setName("Mocha");

        // Act
        boolean result = first.equals(second);

        // Assert
        assertFalse(result);
    }

    @Test
    void testOtherNameNull() {
        // Arrange
        Recipe first = new Recipe();
        first.setName("Latte");

        Recipe second = new Recipe();
        second.setName(null);

        // Act
        boolean result = first.equals(second);

        // Assert
        assertFalse(result);
    }

    @Test
    void testThisNameNull() {
        // Arrange
        Recipe first = new Recipe();
        first.setName(null);

        Recipe second = new Recipe();
        second.setName("Latte");

        // Act
        boolean result = first.equals(second);

        // Assert
        assertFalse(result);
    }

    @Test
    void testBothNamesNull() {
        // Arrange
        Recipe first = new Recipe();
        first.setName(null);

        Recipe second = new Recipe();
        second.setName(null);

        // Act
        boolean result = first.equals(second);

        // Assert
        assertFalse(result);
    }

    @Test
    void testToString() {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setName("Mocha");

        // Act
        String result = recipe.toString();

        // Assert
        assertEquals("Mocha", result);
    }
}