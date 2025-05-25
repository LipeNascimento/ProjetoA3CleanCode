
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FarmTest {

    @Test
    public void testFarmCreation() {
        Farm farm = new Farm(1, "Fazenda Boa Vista", "Interior de SP");
        assertEquals("Fazenda Boa Vista", farm.getName());
        assertEquals("Interior de SP", farm.getLocation());
        assertEquals(1, farm.getId());
    }
}
