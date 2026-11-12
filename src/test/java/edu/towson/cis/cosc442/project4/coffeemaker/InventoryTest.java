package edu.towson.cis.cosc442.project4.coffeemaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void testStartingAmounts() {
        // Arrange: inventory is created in setUp()

        // Act and Assert
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    @ParameterizedTest
    @CsvSource({
        "-10, 0",
        "-1, 0",
        "0, 0",
        "1, 1",
        "20, 20",
        "2147483647, 2147483647"
    })
    void testCoffeeAmount(int input, int expected) {
        // Arrange: inventory starts with 15 units

        // Act
        inventory.setCoffee(input);

        // Assert
        assertEquals(expected, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    @ParameterizedTest
    @CsvSource({
        "-10, 0",
        "-1, 0",
        "0, 0",
        "1, 1",
        "20, 20",
        "2147483647, 2147483647"
    })
    void testMilkAmount(int input, int expected) {
        // Arrange: inventory starts with 15 units

        // Act
        inventory.setMilk(input);

        // Assert
        assertEquals(expected, inventory.getMilk());
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    @ParameterizedTest
    @CsvSource({
        "-10, 0",
        "-1, 0",
        "0, 0",
        "1, 1",
        "20, 20",
        "2147483647, 2147483647"
    })
    void testSugarAmount(int input, int expected) {
        // Arrange: inventory starts with 15 units

        // Act
        inventory.setSugar(input);

        // Assert
        assertEquals(expected, inventory.getSugar());
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getChocolate());
    }

    @ParameterizedTest
    @CsvSource({
        "-10, 0",
        "-1, 0",
        "0, 0",
        "1, 1",
        "20, 20",
        "2147483647, 2147483647"
    })
    void testChocolateAmount(int input, int expected) {
        // Arrange: inventory starts with 15 units

        // Act
        inventory.setChocolate(input);

        // Assert
        assertEquals(expected, inventory.getChocolate());
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 0, 0, true",
        "14, 14, 14, 14, true",
        "15, 15, 15, 15, true",
        "16, 0, 0, 0, false",
        "0, 16, 0, 0, false",
        "0, 0, 16, 0, false",
        "0, 0, 0, 16, false",
        "16, 16, 16, 16, false"
    })
    void testEnoughIngredients(int coffee, int milk, int sugar,
            int chocolate, boolean expected) {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setAmtCoffee(coffee);
        recipe.setAmtMilk(milk);
        recipe.setAmtSugar(sugar);
        recipe.setAmtChocolate(chocolate);

        // Act
        boolean result = inventory.enoughIngredients(recipe);

        // Assert
        assertEquals(expected, result);

        // Checking availability of ingredients after the check
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    @Test
    void testToString() {
        // Arrange
        inventory.setCoffee(2);
        inventory.setMilk(4);
        inventory.setSugar(6);
        inventory.setChocolate(8);

        // Act
        String result = inventory.toString();

        // Assert
        assertEquals(
            "Coffee: 2\n"
            + "Milk: 4\n"
            + "Sugar: 6\n"
            + "Chocolate: 8\n",
            result
        );
    }
}