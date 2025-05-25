
package controller;

import model.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventoryControllerTest {

    private InventoryController controller;

    @BeforeEach
    public void setUp() {
        controller = mock(InventoryController.class);
    }

    @Test
    public void testAddInventory() {
        doNothing().when(controller).addInventory("Adubo", 50, "kg");
        controller.addInventory("Adubo", 50, "kg");
        verify(controller, times(1)).addInventory("Adubo", 50, "kg");
    }

    @Test
    public void testGetAllInventory() {
        when(controller.getAllInventory()).thenReturn(List.of(new Inventory(1, "Adubo", 50, "kg")));
        List<Inventory> inventory = controller.getAllInventory();
        assertEquals(1, inventory.size());
        assertEquals("Adubo", inventory.get(0).getItemName());
    }
}
