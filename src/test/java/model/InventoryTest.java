
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    @Test
    public void testInventoryCreation() {
        Inventory item = new Inventory(1, "Semente", 50, "kg");
        assertEquals("Semente", item.getItemName());
        assertEquals(50, item.getQuantity());
        assertEquals("kg", item.getUnit());
        assertEquals(1, item.getId());
    }
}
