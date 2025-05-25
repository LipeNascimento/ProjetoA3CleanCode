
package controller;

import model.Farm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FarmControllerTest {

    private FarmController controller;

    @BeforeEach
    public void setUp() {
        controller = mock(FarmController.class);
    }

    @Test
    public void testAddFarm() {
        doNothing().when(controller).addFarm("Fazenda A", "SP", "Interior", 10.5);
        controller.addFarm("Fazenda A", "SP", "Interior", 10.5);
        verify(controller, times(1)).addFarm("Fazenda A", "SP", "Interior", 10.5);
    }

    @Test
    public void testGetAllFarms() {
        when(controller.getAllFarms()).thenReturn(List.of(new Farm(1, "Fazenda A", "Interior")));
        List<Farm> farms = controller.getAllFarms();
        assertEquals(1, farms.size());
        assertEquals("Fazenda A", farms.get(0).getName());
    }
}
