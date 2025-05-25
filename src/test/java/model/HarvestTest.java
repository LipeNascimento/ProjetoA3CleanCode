
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HarvestTest {

    @Test
    public void testHarvestCreation() {
        Harvest harvest = new Harvest(1, "Milho", 1, 100, "2025-05-25");
        assertEquals("Milho", harvest.getCropName());
        assertEquals(1, harvest.getFarmId());
        assertEquals(100, harvest.getQuantity());
        assertEquals("2025-05-25", harvest.getDate());
        assertEquals(1, harvest.getId());
    }
}
